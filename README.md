# Projeto Spring Boot 3 - Ecommerce

Este é um projeto de ecommerce desenvolvido com **Spring Boot 3**. Ele utiliza um banco de dados **MySQL** para armazenamento de dados e inclui uma configuração inicial para popular o banco de dados automaticamente ao rodar o projeto. A documentação da API é gerada automaticamente pelo **Swagger UI**.

---

## Tecnologias Utilizadas

- **Spring Boot 3**: Framework principal para desenvolvimento da aplicação.
- **MySQL 8.0**: Banco de dados relacional para armazenamento de dados.
- **Swagger UI**: Documentação interativa da API.
- **Spring Security**: Autenticação e autorização com **JWT** (JSON Web Token).
- **Flyway**: Controle de versão de scripts de migração de banco de dados.
- **Maven**: Gerenciamento de dependências e build do projeto.

---

## Pré-Requisitos

Antes de rodar o projeto, certifique-se de ter os seguintes requisitos instalados:

- **Java 17 ou superior**: Necessário para executar a aplicação Spring Boot.
- **MySQL Workbench** (opcional): Para gerenciar o banco de dados localmente.
- **Maven**: Para compilar e rodar o projeto.
- **Docker** (opcional): Para rodar o MySQL em um container.

---

## Configuração do Banco de Dados

O projeto utiliza o **MySQL** como banco de dados. Você pode configurá-lo de duas maneiras: usando **Docker** ou **MySQL Workbench**.

### Opção 1: Usando Docker (Recomendado)

1. **Instale o Docker** em sua máquina, caso ainda não tenha.
2. Na raiz do projeto, há um arquivo `docker-compose.yml` configurado para subir um container do MySQL.
3. Execute o seguinte comando para iniciar o banco de dados:

   ```bash
   docker-compose up

Isso criará um container do MySQL com as seguintes configurações:

    Banco de dados: fsecommerce

    Usuário: usuario

    Senha: senha

    Porta: 3306

    O banco de dados estará pronto para uso.

### Opção 2: Usando MySQL Workbench (Sem Docker)

   Crie um banco de dados chamado fsecommerce no MySQL Workbench.

   Crie um usuário com as seguintes credenciais:

        Usuário: seu_usuario
        Senha: sua_senha

   Atualize o arquivo application.properties (ou application.yml) com as credenciais do banco de dados:
   
    spring.datasource.url=jdbc:mysql://localhost:3306/fsecommerce
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

## Rodando o Projeto
### Passo 1: Clone o Repositório

Clone o repositório do projeto:
bash
Copy

git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio

### Passo 2: Execute a Aplicação

Com o banco de dados configurado, execute a aplicação Spring Boot:

   Usando Maven:
    bash
        
    ./mvnw spring-boot:run

Usando Gradle:
    bash
    
    ./gradlew bootRun

A aplicação será iniciada na porta 8080 por padrão.
Inserção de Dados Iniciais

Ao rodar a aplicação pela primeira vez, os dados iniciais serão inseridos automaticamente no banco de dados. Isso inclui:

   Usuários:

        Admin: admin@ecommerce.com (senha: senha_admin, role: ROLE_ADMIN)

        Cliente: cliente1@ecommerce.com (senha: senha_cliente, role: ROLE_USUARIO)

   Categorias:

        Eletrônicos

        Computadores

        Acessórios

   Produtos:

        Smartphone

        Notebook Gamer

        Mouse Óptico

        Teclado Mecânico

        Fone de Ouvido Bluetooth

        Monitor 4K

## Acessando o Swagger UI

Após rodar a aplicação, acesse a documentação interativa da API no Swagger UI:

**http://localhost:8080/fsecommerce/swagger-ui/index.html**

## Usuários e Senhas Iniciais

Para testar os endpoints protegidos, utilize os seguintes dados de login:

| **Usuário** | **Email**               | **Senha**       | **Role**      |
|-------------|-------------------------|-----------------|---------------|
| Admin       | `admin@ecommerce.com`   | `senha_admin`   | `ROLE_ADMIN`  |
| Cliente     | `cliente1@ecommerce.com`| `senha_cliente` | `ROLE_USUARIO`|

## Endpoints Disponíveis

### Aqui estão alguns dos principais endpoints disponíveis na API:
Usuários

    POST /usuarios: Criar um novo usuário.

    GET /usuarios: Listar todos os usuários.

Produtos

    POST /produtos: Criar um novo produto.

    GET /produtos: Listar todos os produtos.

Pedidos

    POST /pedidos: Criar um novo pedido.

    GET /pedidos: Listar todos os pedidos.

## Estrutura do Projeto

A estrutura do projeto está organizada da seguinte forma:

    src/main/java: Código-fonte da aplicação.

    src/main/resources:

        application.properties: Configurações da aplicação.

        schema.sql: Scripts SQL para inicialização do banco de dados.

    docker-compose.yml: Configuração do MySQL com Docker.

    pom.xml: Dependências do Maven.
