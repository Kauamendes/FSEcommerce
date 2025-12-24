INSERT INTO tenants (id, nome, slug, ativo, criado_em, criado_por, alterado_por, alterado_em)
VALUES (1, 'Loja Matriz A', 'loja-a', TRUE, CURRENT_TIMESTAMP, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP),
       (2, 'Loja Matriz B', 'loja-b', TRUE, CURRENT_TIMESTAMP, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP);

INSERT INTO usuario (id, tenant_id, nome, email, senha, role, criado_por, alterado_por, alterado_em, ativo)
VALUES (100, 1, 'Admin A', 'admin@tenantA.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_ADMIN', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (101, 2, 'Admin B', 'admin@tenantB.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_ADMIN', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (102, 1, 'User A', 'user@tenantA.com', '$2a$10$XaCTwPiSMJZbIDscDhFM5.99P2vtg2XkIPJTQkw5CHEjKoGJN2yuO', 'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

INSERT INTO categoria (id, tenant_id, nome, descricao, criado_por, alterado_por, alterado_em, ativo)
VALUES (200, 1, 'Eletrônicos A', 'Desc A', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (201, 2, 'Eletrônicos B', 'Desc B', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

INSERT INTO produto (id, tenant_id, nome, descricao, preco, categoria_id, quantidade, criado_por, alterado_por, alterado_em, ativo)
VALUES (300, 1, 'Smartphone A', 'Celular A', 1000.00, 200, 10, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (301, 2, 'Smartphone B', 'Celular B', 2000.00, 201, 10, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);