CREATE TABLE usuario (
                         id VARCHAR(36) PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         role VARCHAR(50) NOT NULL,
                         criado_por   VARCHAR(255)                                                    NOT NULL,
                         criado_em    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
                         alterado_por VARCHAR(255)                                                    NOT NULL,
                         alterado_em  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                         version      INT       DEFAULT 0                                             NOT NULL
);

CREATE TABLE categoria (
                           id VARCHAR(36) PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           descricao TEXT,
                           criado_por   VARCHAR(255)                                                    NOT NULL,
                           criado_em    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
                           alterado_por VARCHAR(255)                                                    NOT NULL,
                           alterado_em  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                           version      INT       DEFAULT 0                                             NOT NULL
);

CREATE TABLE produto (
                         id VARCHAR(36) PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         preco DECIMAL(10,2) NOT NULL,
                         categoria_id VARCHAR(36) NOT NULL,
                         quantidade INT NOT NULL DEFAULT 0,
                         quantidade_reservada INT                                                             NOT NULL DEFAULT 0,
                         criado_por           VARCHAR(255)                                                    NOT NULL,
                         criado_em            TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
                         alterado_por         VARCHAR(255)                                                    NOT NULL,
                         alterado_em          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                         version              INT       DEFAULT 0                                             NOT NULL,
                         FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE
);

CREATE TABLE pedido (
                        id VARCHAR(36) PRIMARY KEY,
                        usuario_id VARCHAR(36) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        subtotal DECIMAL(10,2) NOT NULL,
                        criado_por   VARCHAR(255)                                                    NOT NULL,
                        criado_em    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
                        alterado_por VARCHAR(255)                                                    NOT NULL,
                        alterado_em  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
                        version      INT       DEFAULT 0                                             NOT NULL,
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

CREATE INDEX idx_usuario_email ON usuario (email);
CREATE INDEX idx_usuario_id ON usuario (id);

CREATE INDEX idx_pedido_usuario_id ON pedido (usuario_id);
CREATE INDEX idx_pedido_status ON pedido (status);
CREATE INDEX idx_pedido_criado_em ON pedido (criado_em);
CREATE INDEX idx_pedido_usuario_criado_em ON pedido (usuario_id, criado_em);
CREATE INDEX idx_pedido_usuario_id_subtotal ON pedido (usuario_id, subtotal);
CREATE INDEX idx_pedido_criado_em_subtotal ON pedido (criado_em, subtotal);

CREATE INDEX idx_produto_categoria_id ON produto (categoria_id);
CREATE INDEX idx_produto_nome ON produto (nome);

CREATE INDEX idx_categoria_nome ON categoria (nome);

CREATE INDEX idx_pedido_produto_pedido_id ON pedido_produto (pedido_id);
CREATE INDEX idx_pedido_produto_produto_id ON pedido_produto (produto_id);