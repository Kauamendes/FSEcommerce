CREATE TABLE tenants (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    criado_por VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alterado_por VARCHAR(255) NOT NULL,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT FALSE,
    criado_por VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alterado_por VARCHAR(255) NOT NULL,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT uk_usuario_email_tenant UNIQUE (email, tenant_id),
    CONSTRAINT fk_usuario_tenant FOREIGN KEY (tenant_id) REFERENCES tenants (id)
);

CREATE TABLE categoria (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    ativo BOOLEAN DEFAULT FALSE,
    criado_por VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alterado_por VARCHAR(255) NOT NULL,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_categoria_tenant FOREIGN KEY (tenant_id) REFERENCES tenants (id)
);

CREATE TABLE produto (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    categoria_id BIGINT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    quantidade_reservada INT NOT NULL DEFAULT 0,
    ativo BOOLEAN DEFAULT FALSE,
    criado_por VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alterado_por VARCHAR(255) NOT NULL,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id),
    CONSTRAINT fk_produto_tenant FOREIGN KEY (tenant_id) REFERENCES tenants (id)
);

CREATE TABLE pedido (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    criado_por VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alterado_por VARCHAR(255) NOT NULL,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    CONSTRAINT fk_pedido_tenant FOREIGN KEY (tenant_id) REFERENCES tenants (id)
);

CREATE TABLE pedido_produto (
    id BIGINT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_pp_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_pp_produto FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE INDEX idx_usuario_tenant_email ON usuario (tenant_id, email);
CREATE INDEX idx_categoria_tenant_nome ON categoria (tenant_id, nome);
CREATE INDEX idx_produto_tenant_cat ON produto (tenant_id, categoria_id);
CREATE INDEX idx_produto_tenant_nome ON produto (tenant_id, nome);
CREATE INDEX idx_pedido_tenant_status ON pedido (tenant_id, status);
CREATE INDEX idx_pedido_tenant_usuario ON pedido (tenant_id, usuario_id);
CREATE INDEX idx_pedido_produto_pedido ON pedido_produto (pedido_id);