describe('Garantia de Isolamento Total por Tenant', () => {
  const tenants = [
    { id: 'A', admin: 'admin@tenantA.com', catId: 200 },
    { id: 'B', admin: 'admin@tenantB.com', catId: 201 }
  ];

  let tokens = {};

  before(() => {
    cy.login('admin@tenantA.com', '123').then(t => tokens.adminA = t);
    cy.login('admin@tenantB.com', '123').then(t => tokens.adminB = t);
  });

  it('deve isolar produtos entre tenants', () => {
    const nomeUnico = `Prod-A-${Date.now()}`;

    // Criar no A
    cy.requestAs('POST', '/produtos', tokens.adminA, {
      nome: nomeUnico,
      preco: 50.0,
      categoria: { id: 200 }, // ID do SQL
      quantidade: 10,
      ativo: true
    }).then(res => {
      const idA = res.body.id;

      // Tenant B não deve ver na lista
      cy.requestAs('GET', '/produtos', tokens.adminB).then(resB => {
        const existeNoB = resB.body.content.some(p => p.nome === nomeUnico);
        expect(existeNoB).to.be.false;
      });

      // Tenant B não deve acessar por ID direto
      cy.requestAs('GET', `/produtos/${idA}`, tokens.adminB).then(resGet => {
        expect(resGet.status).to.equal(404);
      });
    });
  });
});