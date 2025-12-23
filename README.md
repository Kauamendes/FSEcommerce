# 🚀 Foursquare Ecommerce API

Plataforma de e-commerce **multi-tenant**, moderna e altamente performática, construída para operar em **escala horizontal**.  
Projetada para atender milhares de lojas de forma isolada, segura e eficiente, compartilhando a mesma infraestrutura.

> Este projeto é voltado para estudos avançados de arquitetura, performance e boas práticas com Spring Boot moderno.

---

## ✨ Principais Características

- Arquitetura **Multi-tenant (Shared Schema)** com isolamento por linha.
- Identificadores numéricos **TSID (Time-Sorted Unique Identifier)**.
- Alta concorrência com **Virtual Threads (Project Loom)**.
- Segurança baseada em **JWT**, com `tenantId` embutido no token.
- Migrações de banco versionadas e prontas para evolução.

---

## 🛠️ Stack Tecnológica

- **Java 25**
- **Spring Boot 4**
- **Jetty Web Server**
- **Hibernate 7**
- **MySQL 8.4 (LTS)**
- **Spring Security + JWT**
- **Flyway**
- **Docker & Docker Compose**

> ℹ️ **Nota**: O projeto utiliza **Jetty** como servidor web embutido, uma vez que o **Undertow não possui suporte oficial ao Spring Boot 4** no momento.

---

## 🏗️ Arquitetura de Identificadores (TSID)

O projeto utiliza **TSIDs** em vez de UUIDs ou IDs sequenciais tradicionais.

### Por quê TSID?

- **Performance**: Apenas 8 bytes (`BIGINT`).
- **Ordenação temporal natural** (melhor uso de índices B-Tree).
- **Geração distribuída**, sem dependência do banco.
- **Preparado para sharding** e ambientes distribuídos.

---

## 🧩 Multi-tenancy Transparente

O isolamento de dados ocorre automaticamente na camada de persistência:

1. O usuário se autentica e recebe um **JWT**.
2. O `tenantId` é extraído do token.
3. O valor é armazenado no `TenantContext`.
4. O Hibernate aplica o filtro de tenant em todas as queries.

Exemplo prático:

```sql
SELECT * FROM produto;
```

é convertido automaticamente em:

```sql
SELECT * FROM produto WHERE tenant_id = ?;
```

Nenhuma lógica adicional é necessária nos repositórios.

---

## 🚀 Executando o Projeto Localmente

### Pré-requisitos

- JDK 25
- Maven 3.9+
- Docker
- Docker Compose

---

### 1️⃣ Subir o Banco de Dados

```bash
docker-compose up -d
```

---

### 2️⃣ Compilar e Rodar a Aplicação

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📖 Documentação da API

A API expõe documentação interativa via **Swagger UI**:

👉 http://localhost:8080/swagger-ui/index.html

---

## 🔐 Dados Iniciais (Seed)

Durante o primeiro boot, o Flyway cria automaticamente um tenant inicial para testes:

| Campo           | Valor                 |
|-----------------|-----------------------|
| Tenant Name     | Loja Matriz           |
| Tenant ID       | Gerado via TSID       |
| Admin Email     | admin@fsecommerce.com |
| Admin Password  | senha_admin           |

---

## 📁 Estrutura do Projeto

```
src/main/java/.../config/id
    Configuração de TSID e geração de IDs

src/main/java/.../config/tenant
    Contexto do tenant e filtros de segurança

src/main/java/.../domain
    Entidades de domínio multi-tenant

src/main/resources/db/migration
    Migrações Flyway (SQL)
```

---

## 📌 Status do Projeto

🚧 Em evolução contínua — foco em arquitetura, performance e escalabilidade.

Discussões técnicas, sugestões e contribuições são bem-vindas.
