describe('Garantia de Isolamento Total por Tenant (Full Stack)', () => {
  const tenants = {
    moda: { admin: 'admin@fashion.com', catId: 200, prodId: 300, userId: 1 },
    livros: { admin: 'admin@saber.com', catId: 205, prodId: 310, userId: 2 }
  };

  let tokens = {};

  before(() => {
    cy.login(tenants.moda.admin, '123').then(t => tokens.moda = t);
    cy.login(tenants.livros.admin, '123').then(t => tokens.livros = t);
  });

  // --- 1. ISOLAMENTO DE CATEGORIAS ---
  it('Deve isolar Categorias: Tenant Livros não deve ver categorias de Moda', () => {
    cy.requestAs('GET', '/categorias', tokens.livros).then(res => {
      const categorias = res.body.content || res.body;
      const temModa = categorias.some(c => c.id === tenants.moda.catId);
      expect(temModa).to.be.false;
    });

    cy.requestAs('GET', `/categorias/${tenants.moda.catId}`, tokens.livros).then(res => {
      expect(res.status).to.equal(404);
    });
  });

  // --- 2. ISOLAMENTO DE USUÁRIOS ---
  it('Deve isolar Usuários: Admins não devem listar usuários de outros tenants', () => {
    cy.requestAs('GET', '/usuarios', tokens.moda).then(res => {
      const usuarios = res.body.content || res.body;
      // O admin de Moda não deve ver o usuário de Livros
      const temUserLivros = usuarios.some(u => u.email === tenants.livros.admin);
      expect(temUserLivros).to.be.false;
    });
  });

// --- 3. ISOLAMENTO DE PEDIDOS (CRÍTICO) ---
it('Deve isolar Pedidos: Impedir acesso e criação cross-tenant', () => {
  // Tentativa de ataque: Tenant Moda tenta criar pedido usando PRODUTO de Livros
  cy.requestAs('POST', '/pedidos', tokens.moda, {
    usuario: { id: tenants.moda.userId },
    pedidoProdutos: [
      {
        produto: { id: tenants.livros.prodId },
        quantidade: 1
      }
    ],
    status: "PENDENTE"
  }).then(res => {
    expect(res.status).to.be.oneOf([400, 404]);
  });
});

  // --- 4. TENTATIVA DE BYPASS VIA UPDATE ---
  it('Deve impedir alteração de Usuário de outro tenant', () => {
    cy.requestAs('PUT', `/usuarios/${tenants.livros.userId}`, tokens.moda, {
      nome: "Hacker",
      email: "hacker@moda.com"
    }).then(res => {
      expect(res.status).to.be.oneOf([404, 403]);
    });
  });
});