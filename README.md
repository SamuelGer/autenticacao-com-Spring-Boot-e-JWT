# Spring Boot JWT Auth

API de autenticação back-end desenvolvida com Java e Spring Boot, utilizando JWT (JSON Web Token) para autenticação stateless.

Projeto desenvolvido como aprendizado prático de desenvolvimento back-end.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.0.4
- Spring Security
- JWT (JJWT 0.12.x)
- H2 Database (banco em memória)
- Lombok
- Maven

## Endpoints

| Método | Rota | Descrição | Autenticação |
|--------|------|-----------|--------------|
| POST | `/auth/register` | Cadastro de novo usuário | Não |
| POST | `/auth/login` | Login e geração do token JWT | Não |
| GET | `/user/me` | Retorna dados do usuário logado | Sim (Bearer Token) |

##  Estrutura do projeto

```
src/main/java/com/example/meu_primeiro_springboot/
├── controller/        # AuthController, UserController
├── service/           # UserService
├── model/             # User
├── repository/        # UserRepository
├── DTO/               # LoginDTO, RegisterDTO
├── config_security/   # SecurityConfig, JwtFilter, JwtUtil
└── exceptions/        # GlobalExceptionHandler, exceções customizadas
```
