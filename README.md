# 🔗 API de Usuários e Links com Autenticação JWT

Projeto desenvolvido para prática de backend com **Java + Spring Boot**, evoluindo de um CRUD de usuários para um sistema estilo **Linktree**, onde cada usuário pode gerenciar seus próprios links, com autenticação via **JWT (JSON Web Token)**.

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

### 👤 Usuários

* ✅ Cadastro de usuários
* ✅ Login com autenticação
* ✅ Atualização de usuário
* ✅ Exclusão de usuário
* ✅ Validação de dados (email, senha, etc.)

### 🔗 Links

* ✅ Criação de links associados a usuários
* ✅ Listagem de links por usuário
* ✅ Relacionamento **1:N (User → Links)**
* ✅ Criação de link usando apenas `userId`
* ✅ Estrutura pronta para evolução (ex: ordenação de links)

### 🔐 Segurança

* ✅ Geração de token JWT
* ✅ Validação de token via filtro
* ✅ Rotas protegidas
* ✅ Autenticação stateless (sem sessão)

### 🧩 Arquitetura

* ✅ Uso de DTOs (Request / Response)
* ✅ Separação de responsabilidades
* ✅ Evita exposição direta das entidades

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
├── controller     # Endpoints da API
├── service        # Regras de negócio
├── repository     # Acesso ao banco
├── entity         # Entidades JPA (User, Link)
├── dto            # DTOs de entrada e saída
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

#### Listar usuários (com links)

```
GET /users
```

#### Buscar por ID

```
GET /users/{id}
```

---

### 🔗 Links

#### Criar link

```
POST /links
```

Body:

```json
{
  "name": "YouTube",
  "url": "https://www.youtube.com/",
  "userId": 1
}
```

#### Listar links por usuário

```
GET /users/{id}
```

---

## 🛡️ Segurança

* Senhas criptografadas com **BCrypt**
* Rotas protegidas com Spring Security
* Filtro JWT para validação de requisições
* Estrutura pronta para autenticação baseada no usuário logado

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
* Relacionamento correto entre entidades (JPA)
* Evita loop infinito com serialização JSON
* Separação em camadas (Controller / Service / Repository)
* Código preparado para escalar

---

## 🧠 Objetivo do Projeto

Projeto criado com foco em:

* Aprender autenticação com Spring Security
* Entender JWT na prática
* Trabalhar com relacionamentos no banco (JPA)
* Evoluir de CRUD simples para um sistema mais real (estilo Linktree)
* Se preparar para vagas de desenvolvedor backend júnior

---

## 🚀 Possíveis melhorias

* [ ] Associar links automaticamente ao usuário logado (JWT)
* [ ] Ordenação de links (drag and drop estilo Linktree)
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
