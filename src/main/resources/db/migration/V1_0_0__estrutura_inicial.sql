CREATE TABLE usuario (
                         id VARCHAR(36) PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         role VARCHAR(50) NOT NULL,
                         criado_por VARCHAR(255),
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         alterado_por VARCHAR(255),
                         alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         version INT DEFAULT 0
);

CREATE TABLE categoria (
                           id VARCHAR(36) PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           descricao TEXT,
                           criado_por VARCHAR(255),
                           criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           alterado_por VARCHAR(255),
                           alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           version INT DEFAULT 0
);

CREATE TABLE produto (
                         id VARCHAR(36) PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         preco DECIMAL(10,2) NOT NULL,
                         categoria_id VARCHAR(36) NOT NULL,
                         quantidade INT NOT NULL DEFAULT 0,
                         criado_por VARCHAR(255),
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         alterado_por VARCHAR(255),
                         alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         version INT DEFAULT 0,
                         FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE
);

CREATE TABLE pedido (
                        id VARCHAR(36) PRIMARY KEY,
                        usuario_id VARCHAR(36) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        subtotal DECIMAL(10,2) NOT NULL,
                        criado_por VARCHAR(255),
                        criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        alterado_por VARCHAR(255),
                        alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        version INT DEFAULT 0,
                        FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE pedido_produto (
                                id VARCHAR(36) PRIMARY KEY,
                                pedido_id VARCHAR(36) NOT NULL,
                                produto_id VARCHAR(36) NOT NULL,
                                quantidade INT NOT NULL,
                                preco_unitario DECIMAL(10,2) NOT NULL,
                                FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
                                FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);
