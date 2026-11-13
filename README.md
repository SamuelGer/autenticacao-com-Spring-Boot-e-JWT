# Spring Boot JWT Auth

API de autenticação back-end desenvolvida com Java e Spring Boot, utilizando JWT (JSON Web Token) para autenticação stateless.

Projeto desenvolvido como aprendizado prático de desenvolvimento back-end.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.0.4
- Spring Security
- JWT (JJWT 0.12.x)
- MySQL Database
- Lombok
- Maven

## Endpoints

| Método | Rota | Descrição | Autenticação |
|--------|------|-----------|--------------|
| POST | `/v1/auth/register` | Cadastro de novo usuário | Não |
| POST | `/v1/auth/login` | Login com email ou username ("identifier": no JSON) e geração do token JWT | Não |
| GET | `/v1/user/me` | Retorna dados do usuário logado | Sim (Bearer Token) |
| GET | `/v1/admin/dashboard` | Área administrativa | Sim (Bearer Token + ADMIN) |
| PATCH | `/v1/auth/updateUser`|  Atualiza username, email ou senha do usuário logado e retorna um novo token | Sim (Bearer Token) |

##  Estrutura do projeto

```
src/main/java/com/example/meu_primeiro_springboot/
├── controller/        # AuthController, UserController, AdminController
├── service/           # UserService
├── model/             # User, Roles
├── repository/        # UserRepository
├── DTO/               # LoginDTO, RegisterDTO, UpdateUserDTO
├── config_security/   # SecurityConfig, JwtFilter, JwtUtil, DataInitializer
└── exceptions/        # GlobalExceptionHandler, exceções customizadas
```
