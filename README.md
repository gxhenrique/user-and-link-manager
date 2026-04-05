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

```text
src/main/java/com/example/usuarios
│
├── controller     # Endpoints da API (UserController, LinkController, AuthController)
├── service        # Regras de negócio (UserService, LinkService)
├── repository     # Interfaces JPA para acesso ao banco
├── entity         # Entidades (User, Link)
├── dto            # DTOs de usuário (request/response)
├── dtolink        # DTOs específicos de Link
├── security       # Configurações do Spring Security + JWT
├── componet       # Filtro JWT e componentes auxiliares (ex: JwtFilter)
├── exceptions     # Tratamento de exceções personalizadas
```

---

## 🧩 Organização da Arquitetura

O projeto segue o padrão em camadas:

* **Controller** → recebe requisições HTTP
* **Service** → contém a lógica de negócio
* **Repository** → comunicação com o banco
* **DTO** → controla entrada e saída de dados
* **Entity** → representação das tabelas no banco

---

## 🔗 Relacionamento das Entidades

O sistema possui um relacionamento:

* **User (1) → (N) Links**

Ou seja:

* Um usuário pode ter vários links
* Cada link pertence a um único usuário

---

## ⚙️ Fluxo de criação de Link

1. Cliente envia:

```json
{
  "name": "YouTube",
  "url": "https://youtube.com",
  "userId": 1
}
```

2. O backend:

* Busca o usuário pelo `userId`
* Associa o link ao usuário
* Salva no banco

---

## 🛡️ Segurança (JWT)

* Autenticação stateless (sem sessão)
* Filtro JWT intercepta requisições
* Token validado a cada request
* Rotas protegidas

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
