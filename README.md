# Blog API

## Descrição

Este projeto é uma API RESTful para um sistema de gerenciamento de blog, permitindo aos usuários criar, editar e excluir posts, categorias, tags e gerenciar a autenticação via JWT (JSON Web Token). A aplicação foi construída com **Spring Boot**, **Spring Security**, **Spring Data JPA**, **JWT** e **MapStruct** para mapeamento de DTOs.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** 3.x
- **Spring Security**
- **Spring Data JPA**
- **H2 Database** (para ambiente de desenvolvimento)
- **PostgreSQL** (para ambiente de produção)
- **JWT** (para autenticação)
- **MapStruct** (para mapeamento entre objetos e DTOs)
- **Lombok** (para redução de boilerplate)

## Funcionalidades

- **Autenticação e Autorização**: Usuários podem autenticar-se via email e senha utilizando JWT para gerar e validar tokens.
- **Gestão de Posts**: CRUD completo para posts, com categorias e tags associadas. Filtro por status (publicado ou rascunho), categoria e tags.
- **Gestão de Categorias**: Criação, deleção e listagem de categorias.
- **Gestão de Tags**: Criação, deleção e listagem de tags.
- **Administração de Usuários**: Recuperação de dados de usuários registrados.

## Endpoints

### Autenticação

- **POST /api/auth/login**
    - **Descrição**: Realiza a autenticação e gera um JWT.
    - **Body**:
    ```json
    {
      "email": "user@example.com",
      "password": "password"
    }
    ```

### Posts

- **GET /api/posts**
    - **Descrição**: Lista todos os posts publicados, com filtros opcionais por categoria e tag.
    - **Query Parameters**: `categoryId`, `tagId`

```bash
curl -X GET "http://localhost:8080/api/posts?categoryId=uuid1&tagId=uuid2"

```

    

- **POST /api/posts**
    - **Descrição**: Cria um novo post.
    - **Body**:
    ```json
    {
      "title": "Novo Post",
      "content": "Conteúdo do post...",
      "categoryId": "uuid-da-categoria",
      "tagIds": ["uuid-da-tag-1", "uuid-da-tag-2"]
    }
    ```

- **PUT /api/posts/{id}**
    - **Descrição**: Atualiza um post existente.
    - **Body**:
    ```json
    {
      "title": "Título Atualizado",
      "content": "Conteúdo do post atualizado...",
      "categoryId": "uuid-da-categoria",
      "tagIds": ["uuid-da-tag-1", "uuid-da-tag-2"]
    }
    ```

- **DELETE /api/posts/{id}**
    - **Descrição**: Deleta um post existente.

### Categorias

- **GET /api/categories**
    - **Descrição**: Lista todas as categorias.

- **POST /api/categories**
    - **Descrição**: Cria uma nova categoria.
    - **Body**:
    ```json
    {
      "name": "Tecnologia"
    }
    ```

- **PUT /api/categories/{id}**
    - **Descrição**: Atualiza uma categoria existente.
    - **Body**:
    ```json
    {
      "name": "Tecnologia Atualizada"
    }
    ```

- **DELETE /api/categories/{id}**
    - **Descrição**: Deleta uma categoria existente.

### Tags

- **GET /api/tags**
    - **Descrição**: Lista todas as tags.

- **POST /api/tags**
    - **Descrição**: Cria novas tags.
    - **Body**:
    ```json
    {
      "tags": ["Java", "Spring Boot"]
    }
    ```

- **DELETE /api/tags/{id}**
    - **Descrição**: Deleta uma tag existente.

### Usuários

- **GET /api/users/{id}**
    - **Descrição**: Recupera os dados de um usuário por ID.

## Como Rodar a Aplicação

### Pré-Requisitos

- **Java 21** ou superior
- **Maven** instalado

### Configuração do Banco de Dados

Se for utilizar **PostgreSQL** em produção, altere a configuração no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```

### Se for usar o H2 Database para desenvolvimento, a configuração será a seguinte:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

```

### Rodando a Aplicação

1. **Clone o repositório**:

    ```bash
    git clone https://github.com/seu_usuario/blog.git
    ```

2. **Navegue até a pasta do projeto**:

    ```bash
    cd blog
    ```

3. **Compile e execute a aplicação**:

    ```bash
    mvn spring-boot:run
    ```

A aplicação estará rodando no **http://localhost:8080**.


