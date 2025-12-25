--- ===========================================================================
--- TENANTS
--- ===========================================================================
INSERT INTO tenants (id, nome, slug, ativo, criado_em, criado_por, alterado_por, alterado_em)
VALUES (1, 'Fashion Style Roupas', 'fashion-style', TRUE, '2025-01-01 10:00:00', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP),
       (2, 'Livraria Saber Infinito', 'saber-infinito', TRUE, '2025-01-01 10:00:00', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP);

--- ===========================================================================
--- USUÁRIOS
--- ===========================================================================
INSERT INTO usuario (id, tenant_id, nome, email, senha, role, criado_por, alterado_por, alterado_em, ativo)
VALUES (100, 1, 'Admin Moda', 'admin@fashion.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_ADMIN', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (101, 1, 'João Silva', 'joao@gmail.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (102, 1, 'Ana Moda', 'ana@fashion.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (103, 2, 'Admin Livros', 'admin@saber.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_ADMIN', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (104, 2, 'Carlos Leitor', 'carlos@saber.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (105, 2, 'Beatriz Livros', 'beatriz@saber.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

--- ===========================================================================
--- CATEGORIAS
--- ===========================================================================
-- Moda (Tenant 1)
INSERT INTO categoria (id, tenant_id, nome, descricao, criado_por, alterado_por, alterado_em, ativo) VALUES
(200, 1, 'Masculino', 'Roupas para homens', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(201, 1, 'Feminino', 'Moda feminina', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(202, 1, 'Infantil', 'Roupas para crianças', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(203, 1, 'Acessórios', 'Bolsas, cintos e joias', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(204, 1, 'Calçados', 'Tênis e sapatos', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

-- Livraria (Tenant 2)
INSERT INTO categoria (id, tenant_id, nome, descricao, criado_por, alterado_por, alterado_em, ativo) VALUES
(205, 2, 'Ficção', 'Romances e contos', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(206, 2, 'Não-Ficção', 'Biografias e fatos', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(207, 2, 'Técnicos', 'Programação e Engenharia', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(208, 2, 'Autoajuda', 'Desenvolvimento pessoal', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(209, 2, 'HQs e Mangás', 'Quadrinhos em geral', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

--- ===========================================================================
--- PRODUTOS
--- ===========================================================================
-- Moda (Tenant 1)
INSERT INTO produto (id, tenant_id, nome, descricao, preco, categoria_id, quantidade, criado_por, alterado_por, alterado_em, ativo) VALUES
(300, 1, 'Camiseta Básica', 'Camiseta preta algodão', 49.90, 200, 100, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(301, 1, 'Calça Jeans', 'Jeans azul escuro slim', 129.90, 200, 50, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(302, 1, 'Vestido Midi', 'Vestido estampado floral', 189.90, 201, 30, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(303, 1, 'Bermuda Sarja', 'Bermuda casual bege', 79.00, 200, 40, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(304, 1, 'Sapato Social', 'Couro legítimo preto', 299.00, 204, 20, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(305, 1, 'Bolsa de Couro', 'Bolsa feminina grande', 350.00, 203, 15, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(306, 1, 'Tênis Running', 'Tênis para corrida leve', 199.90, 204, 25, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(307, 1, 'Jaqueta Jeans', 'Jaqueta clássica', 220.00, 201, 10, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(308, 1, 'Meias Kit 3', 'Algodão branco', 29.90, 200, 200, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(309, 1, 'Boné Aba Curva', 'Acessório casual', 59.90, 203, 45, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

-- Livros (Tenant 2)
INSERT INTO produto (id, tenant_id, nome, descricao, preco, categoria_id, quantidade, criado_por, alterado_por, alterado_em, ativo) VALUES
(310, 2, 'Clean Code', 'Código Limpo - Robert Martin', 95.00, 207, 60, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(311, 2, 'O Senhor dos Anéis', 'A Sociedade do Anel', 55.00, 205, 100, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(312, 2, 'Sapiens', 'História da Humanidade', 69.90, 206, 40, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(313, 2, 'Hábitos Atômicos', 'James Clear', 45.00, 208, 120, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(314, 2, 'Batman: Ano Um', 'Graphic Novel DC', 39.90, 209, 35, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(315, 2, 'Effective Java', 'Joshua Bloch', 110.00, 207, 25, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(316, 2, '1984', 'George Orwell', 35.00, 205, 90, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(317, 2, 'Pai Rico Pai Pobre', 'Robert Kiyosaki', 40.00, 208, 150, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(318, 2, 'O Hobbit', 'J.R.R. Tolkien', 45.00, 205, 70, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
(319, 2, 'Naruto Vol 1', 'Masashi Kishimoto', 29.90, 209, 200, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

--- ===========================================================================
--- PEDIDOS (Histórico de Vendas)
--- ===========================================================================
-- Moda (Vendas em Dezembro 2025)
INSERT INTO pedido (id, tenant_id, usuario_id, status, subtotal, criado_em, criado_por, alterado_em, alterado_por) VALUES
(400, 1, 101, 'PAGO', 179.80, '2025-12-01 14:00:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA'),
(401, 1, 102, 'PAGO', 350.00, '2025-12-05 10:30:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA'),
(402, 1, 101, 'AGUARDANDO_PAGAMENTO', 49.90, '2025-12-24 18:00:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA');

-- Livros (Vendas em Dezembro 2025)
INSERT INTO pedido (id, tenant_id, usuario_id, status, subtotal, criado_em, criado_por, alterado_em, alterado_por) VALUES
(403, 2, 104, 'PAGO', 150.00, '2025-12-10 15:00:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA'),
(404, 2, 105, 'PAGO', 29.90, '2025-12-12 09:00:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA'),
(405, 2, 104, 'CANCELADO', 205.00, '2025-12-15 20:00:00', 'SISTEMA', CURRENT_TIMESTAMP, 'SISTEMA');

--- ===========================================================================
--- ITENS DO PEDIDO
--- ===========================================================================
-- Pedido 400 (Moda): Camiseta + Calça
INSERT INTO pedido_produto (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES
(500, 400, 300, 1, 49.90),
(501, 400, 301, 1, 129.90),
(502, 401, 302, 1, 189.90),
(503, 402, 300, 1, 49.90),
(504, 403, 310, 1, 95.00),
(505, 403, 311, 1, 55.00),
(506, 404, 312, 1, 39.90),
(507, 405, 310, 1, 95.00);