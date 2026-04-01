# 🔐 API de Usuários com Autenticação JWT

Projeto desenvolvido para prática de backend com **Java + Spring Boot**, implementando um CRUD completo de usuários com autenticação via **JWT (JSON Web Token)** e boas práticas como uso de DTOs e validações.

---

## 🚀 Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Security
* JWT (JJWT)
* H2 Database
* Maven
* Bean Validation (Jakarta)

---

## 📌 Funcionalidades

* ✅ Cadastro de usuários
* ✅ Login com autenticação
* ✅ Geração de token JWT
* ✅ Validação de token via filtro
* ✅ Rotas protegidas
* ✅ Atualização de usuário
* ✅ Exclusão de usuário
* ✅ Validação de dados (ex: email, senha)
* ✅ Uso de DTOs (Request / Response)

---

## 🔐 Autenticação

A autenticação é feita via **JWT**:

1. O usuário faz login (`/auth/login`)
2. Recebe um token JWT
3. Envia o token nas próximas requisições:

```
Authorization: Bearer SEU_TOKEN
```

---

## 📂 Estrutura do Projeto

```
src/main/java/com/example/usuarios
│
├── controller     # Controllers (endpoints)
├── service        # Regras de negócio
├── repository     # Acesso ao banco
├── entity         # Entidades JPA
├── dto            # DTOs (entrada e saída)
├── security       # Configuração de segurança + filtro JWT
```

---

## 🧪 Banco de Dados

* Utilizado: **H2 (em memória)**
* Acesso:

```
http://localhost:8080/h2-console
```

---

## 📮 Endpoints principais

### 🔑 Autenticação

```
POST /auth/login
```

Body:

```json
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```

---

### 👤 Usuários

#### Criar usuário

```
POST /users
```

#### Listar usuários

```
GET /users
```

#### Buscar por ID

```
GET /users/{id}
```

#### Atualizar usuário

```
PUT /users/{id}
```

#### Deletar usuário

```
DELETE /users/{id}
```

---

## 🛡️ Segurança

* Senhas criptografadas com **BCrypt**
* Rotas protegidas com Spring Security
* Autenticação stateless (sem sessão)
* Filtro JWT para validação de requisições

---

## 📥 Validação de Dados

Utilizando Bean Validation:

```java
@Email
@NotBlank
@Size(min = 6)
```

---

## 💡 Boas práticas aplicadas

* Uso de DTOs para não expor entidades
* Separação de responsabilidades (Controller / Service / Repository)
* Tratamento de autenticação com JWT
* Código organizado para evolução futura

---

## 🧠 Objetivo do Projeto

Projeto criado com foco em:

* Aprender autenticação com Spring Security
* Entender JWT na prática
* Aplicar boas práticas de backend
* Evoluir para nível de desenvolvedor backend júnior

---

## 🚀 Possíveis melhorias

* [ ] Implementar roles (ADMIN / USER)
* [ ] Refresh Token
* [ ] Tratamento global de exceções
* [ ] Testes automatizados
* [ ] Deploy da aplicação

---

## 👨‍💻 Autor

Desenvolvido por **Henrique Guilherme**

* GitHub: https://github.com/gxhenrique
* LinkedIn: https://www.linkedin.com/in/henrique-guilherme-a29498351/

---

## ⭐ Se gostou do projeto

Deixe uma ⭐ no repositório para apoiar!
