INSERT INTO usuario (id, nome, email, senha, role, criado_por, alterado_por)
VALUES (UUID(), 'Admin', 'admin@ecommerce.com', '$2a$12$oeMCFj6aLz8cVXf6R1nWQeM4E8LTdoFvbj6NooZS18W.dMppHLQRq',
        'ROLE_ADMIN', 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Cliente 1', 'cliente1@ecommerce.com', '$2a$10$VQi2iMA9sZ1NJ6ywbUZj3eFXEEQijz1fBJC2YwR4BdOv4LlD.z3Ua',
        'ROLE_USUARIO', 'SISTEMA', 'SISTEMA');

-- Inserindo categorias
INSERT INTO categoria (id, nome, descricao, criado_por, alterado_por)
VALUES (UUID(), 'Eletrônicos', 'Aparelhos e acessórios eletrônicos', 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Computadores', 'Desktops, notebooks e componentes', 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Acessórios', 'Acessórios para eletrônicos, celulares e computadores', 'SISTEMA', 'SISTEMA');

-- Inserindo produtos
INSERT INTO produto (id, nome, descricao, preco, categoria_id, quantidade, criado_por, alterado_por)
VALUES (UUID(), 'Smartphone', 'Celular top de linha', 3999.99, (SELECT id FROM categoria WHERE nome = 'Eletrônicos'),
        10, 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Notebook Gamer', 'Notebook com placa de vídeo dedicada', 5999.99,
        (SELECT id FROM categoria WHERE nome = 'Computadores'), 5, 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Mouse Óptico', 'Mouse com fio para jogos', 129.90,
        (SELECT id FROM categoria WHERE nome = 'Acessórios'), 30, 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90,
        (SELECT id FROM categoria WHERE nome = 'Acessórios'), 25, 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído', 799.90,
        (SELECT id FROM categoria WHERE nome = 'Acessórios'), 15, 'SISTEMA', 'SISTEMA'),
       (UUID(), 'Monitor 4K', 'Monitor ultra HD para jogos e edição de vídeo', 2299.99,
        (SELECT id FROM categoria WHERE nome = 'Computadores'), 10, 'SISTEMA', 'SISTEMA');