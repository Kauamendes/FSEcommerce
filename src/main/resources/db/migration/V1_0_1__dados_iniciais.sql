INSERT INTO usuario (id, nome, email, senha, role) VALUES
                                                       (UUID(), 'Admin', 'admin@ecommerce.com', '$2a$12$oeMCFj6aLz8cVXf6R1nWQeM4E8LTdoFvbj6NooZS18W.dMppHLQRq', 'ADMIN'),
                                                       (UUID(), 'Cliente 1', 'cliente1@ecommerce.com', '$2a$12$/sDNmGLgnBW4HP5.yPVj2.Ze6rAbEF7SY3cZtvs.WyN1ks9XDiNFq', 'USUARIO');

-- Inserindo categorias
INSERT INTO categoria (id, nome, descricao) VALUES
                                                (UUID(), 'Eletrônicos', 'Aparelhos e acessórios eletrônicos'),
                                                (UUID(), 'Computadores', 'Desktops, notebooks e componentes'),
                                                (UUID(), 'Acessórios', 'Acessórios para eletrônicos, celulares e computadores');

-- Inserindo produtos
INSERT INTO produto (id, nome, descricao, preco, categoria_id, quantidade) VALUES
                                                                               (UUID(), 'Smartphone', 'Celular top de linha', 3999.99, (SELECT id FROM categoria WHERE nome = 'Eletrônicos'), 10),
                                                                               (UUID(), 'Notebook Gamer', 'Notebook com placa de vídeo dedicada', 5999.99, (SELECT id FROM categoria WHERE nome = 'Computadores'), 5),
                                                                               (UUID(), 'Mouse Óptico', 'Mouse com fio para jogos', 129.90, (SELECT id FROM categoria WHERE nome = 'Acessórios'), 30),
                                                                               (UUID(), 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90, (SELECT id FROM categoria WHERE nome = 'Acessórios'), 25),
                                                                               (UUID(), 'Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído', 799.90, (SELECT id FROM categoria WHERE nome = 'Acessórios'), 15),
                                                                               (UUID(), 'Monitor 4K', 'Monitor ultra HD para jogos e edição de vídeo', 2299.99, (SELECT id FROM categoria WHERE nome = 'Computadores'), 10);
