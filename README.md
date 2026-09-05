# Movieflix API
[🇺🇸 Read in English](#movieflix-api-1)

API REST para um catálogo de filmes, com autenticação OAuth2/JWT e perfis de usuário. Projeto do curso DevSuperior.

## Tecnologias
Java 21, Spring Boot 3.4.4, Spring Security + OAuth2 Authorization Server, Spring Data JPA, H2

## Domínio
`Genre` → `Movie` → `Review` → `User` → `Role` (`ROLE_VISITOR`, `ROLE_MEMBER`).

## Endpoints
| Rota | Acesso | Descrição |
|---|---|---|
| `POST /oauth2/token` | Público | Autentica (grant `password`) e retorna JWT |
| `GET /genres` | Autenticado | Lista gêneros |
| `GET /movies` | Autenticado | Lista filmes paginados (filtro `genreId` opcional) |
| `GET /movies/{id}` | Autenticado | Detalhes de um filme |
| `POST /reviews` | MEMBER | Cria uma review (`{ "text", "movieId" }`) |
| `GET /users/profile` | Autenticado | Dados do usuário logado |

## Como executar
```bash
./mvnw spring-boot:run
```
Sobe em `http://localhost:8080` no perfil `test`, com H2 e dados de seed. Console H2 em `/h2-console`.

| Usuário | Senha | Perfil |
|---|---|---|
| bob@gmail.com | 123456 | VISITOR |
| ana@gmail.com | 123456 | MEMBER |

Client OAuth2 padrão: `myclientid` / `myclientsecret`.

## Coleção Postman
`Desafio Movieflix casos de uso.postman_collection.json` + `Movieflix env.postman_environment.json` — importe os dois pra testar a API direto.

---

# Movieflix API
[🇧🇷 Leia em Português](#movieflix-api)

REST API for a movie catalog, with OAuth2/JWT authentication and user profiles. DevSuperior course project.

## Technologies
Java 21, Spring Boot 3.4.4, Spring Security + OAuth2 Authorization Server, Spring Data JPA, H2

## Domain
`Genre` → `Movie` → `Review` → `User` → `Role` (`ROLE_VISITOR`, `ROLE_MEMBER`).

## Endpoints
| Route | Access | Description |
|---|---|---|
| `POST /oauth2/token` | Public | Authenticates (`password` grant) and returns a JWT |
| `GET /genres` | Authenticated | Lists genres |
| `GET /movies` | Authenticated | Lists paginated movies (optional `genreId` filter) |
| `GET /movies/{id}` | Authenticated | Movie details |
| `POST /reviews` | MEMBER | Creates a review (`{ "text", "movieId" }`) |
| `GET /users/profile` | Authenticated | Logged-in user's data |

## How to run
```bash
./mvnw spring-boot:run
```
Runs at `http://localhost:8080` on the `test` profile, with H2 and seed data. H2 console at `/h2-console`.

| User | Password | Role |
|---|---|---|
| bob@gmail.com | 123456 | VISITOR |
| ana@gmail.com | 123456 | MEMBER |

Default OAuth2 client: `myclientid` / `myclientsecret`.

## Postman collection
`Desafio Movieflix casos de uso.postman_collection.json` + `Movieflix env.postman_environment.json` — import both to test the API right away.
