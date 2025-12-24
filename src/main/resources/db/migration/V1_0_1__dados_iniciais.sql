-- Inserindo o Tenant, Criado_por e Alterado_por são obrigatórios conforme DDL acima
INSERT INTO tenants (id, nome, slug, ativo, criado_em, criado_por, alterado_por, alterado_em)
VALUES (1, 'Loja Matriz', 'loja-matriz', TRUE, CURRENT_TIMESTAMP, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP);

INSERT INTO usuario (id, tenant_id, nome, email, senha, role, criado_por, alterado_por, alterado_em, ativo)
VALUES (100, 1, 'Admin', 'admin@ecommerce.com', '$2a$12$oeMCFj6aLz8cVXf6R1nWQeM4E8LTdoFvbj6NooZS18W.dMppHLQRq',
        'ROLE_ADMIN', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (101, 1, 'Cliente 1', 'cliente1@ecommerce.com', '$2a$10$VQi2iMA9sZ1NJ6ywbUZj3eFXEEQijz1fBJC2YwR4BdOv4LlD.z3Ua',
        'ROLE_USUARIO', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

INSERT INTO categoria (id, tenant_id, nome, descricao, criado_por, alterado_por, alterado_em, ativo)
VALUES (200, 1, 'Eletrônicos', 'Aparelhos e acessórios eletrônicos', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (201, 1, 'Computadores', 'Desktops, notebooks e componentes', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (202, 1, 'Acessórios', 'Acessórios para eletrônicos, celulares e computadores', 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);

INSERT INTO produto (id, tenant_id, nome, descricao, preco, categoria_id, quantidade, criado_por, alterado_por, alterado_em, ativo)
VALUES (300, 1, 'Smartphone', 'Celular top de linha', 3999.99, 200, 10, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (301, 1, 'Notebook Gamer', 'Notebook com placa de vídeo dedicada', 5999.99, 201, 5, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (302, 1, 'Mouse Óptico', 'Mouse com fio para jogos', 129.90, 202, 30, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (303, 1, 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90, 202, 25, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (304, 1, 'Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído', 799.90, 202, 15, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE),
       (305, 1, 'Monitor 4K', 'Monitor ultra HD para jogos e edição de vídeo', 2299.99, 201, 10, 'SISTEMA', 'SISTEMA', CURRENT_TIMESTAMP, TRUE);