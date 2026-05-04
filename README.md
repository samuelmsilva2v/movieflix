# Movieflix API

REST API para uma plataforma de catálogo de filmes com autenticação OAuth2 e controle de acesso por perfis de usuário.

## Tecnologias

- Java 21
- Spring Boot 3.4.4
- Spring Security + OAuth2 Authorization Server
- Spring Data JPA
- H2 (banco em memória para testes)
- Maven

## Modelo de Domínio

```
Genre 1 ---< Movie >--- Review >--- User >--- Role
```

- **Genre**: categorias de filmes (Comédia, Terror, Drama, etc.)
- **Movie**: título, subtítulo, ano, imagem, sinopse e gênero
- **Review**: texto de avaliação vinculado a um filme e a um usuário
- **User**: dados de autenticação e perfil
- **Role**: perfis de acesso (`ROLE_VISITOR`, `ROLE_MEMBER`)

## Perfis de Usuário

| Perfil | Permissões |
|---|---|
| `VISITOR` | Listar filmes, listar gêneros, ver detalhes do filme, consultar próprio perfil |
| `MEMBER` | Tudo do VISITOR + criar reviews |

## Endpoints

### Autenticação

```
POST /oauth2/token
```

Autenticação via grant type customizado `password`. Retorna um JWT Bearer token.

**Body (form-data):**
```
username=<email>
password=<senha>
grant_type=password
```

**Headers:**
```
Authorization: Basic <base64(client_id:client_secret)>
```

---

### Gêneros

```
GET /genres
```
Retorna a lista de todos os gêneros. Requer autenticação.

---

### Filmes

```
GET /movies?genreId={id}&page=0&size=10
```
Lista filmes paginados. O filtro por `genreId` é opcional. Requer autenticação.

```
GET /movies/{id}
```
Retorna os detalhes completos de um filme. Requer autenticação.

---

### Reviews

```
POST /reviews
```
Cria uma review para um filme. Requer perfil `MEMBER`.

**Body (JSON):**
```json
{
  "text": "Texto da avaliação",
  "movieId": 1
}
```

---

### Usuário

```
GET /users/profile
```
Retorna os dados do usuário autenticado. Requer `VISITOR` ou `MEMBER`.

## Como Executar

**Pré-requisitos:** Java 21 e Maven instalados.

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080` com o perfil `test` (banco H2 em memória).

O console H2 fica disponível em `http://localhost:8080/h2-console`.

## Dados de Teste

O arquivo `import.sql` carrega dados iniciais automaticamente no perfil `test`.

| Usuário | Senha | Perfil |
|---|---|---|
| bob@gmail.com | 123456 | VISITOR |
| ana@gmail.com | 123456 | MEMBER |

**Credenciais OAuth2:**
- Client ID: `myclientid`
- Client Secret: `myclientsecret`

## Variáveis de Ambiente

| Variável | Padrão | Descrição |
|---|---|---|
| `APP_PROFILE` | `test` | Perfil ativo da aplicação |
| `CLIENT_ID` | `myclientid` | ID do cliente OAuth2 |
| `CLIENT_SECRET` | `myclientsecret` | Secret do cliente OAuth2 |
| `JWT_DURATION` | `86400` | Duração do token JWT em segundos |
| `CORS_ORIGINS` | `http://localhost:3000,http://localhost:5173` | Origens permitidas pelo CORS |

## Testes

```bash
./mvnw test
```

Os testes de integração usam MockMvc com banco H2 e rollback automático por transação.

## Coleção Postman

O repositório inclui uma coleção Postman com todos os casos de uso mapeados:

- `Desafio Movieflix casos de uso.postman_collection.json`
- `Movieflix env.postman_environment.json`

Importe os dois arquivos no Postman para testar a API imediatamente.