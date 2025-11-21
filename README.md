# 🚀 WorkMind SkillQuest API
### 🧠 Upskilling, Reskilling e Gamificação para as Carreiras de 2030+

---

## 📘 Índice
- [📌 Sobre o Projeto](#-sobre-o-projeto)
- [👥 Integrantes](#-integrantes)
- [🌱 ODS da ONU](#-ods-da-onu)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [⚙️ Configuração do Ambiente](#️-configuração-do-ambiente)
- [🔌 Endpoints](#-endpoints)
- [🧪 Como Testar no Postman](#-como-testar-no-postman)
- [📄 Licença](#-licença)

---

## 📌 Sobre o Projeto

A **WorkMind SkillQuest** é uma API RESTful criada como solução para o **Global Solution 2025**, focado no *futuro do trabalho*.

Ela une:
- 🧠 Trilhas gamificadas de Upskilling e Reskilling
- 🏆 Ranking e pontuação (XP)
- 🧩 Sugestões inteligentes de missões (mock IA)
- 🔐 Autenticação via **JWT**
- ❤️ Monitoramento de bem-estar do usuário

Tudo isso para preparar profissionais para carreiras de **2030+**.

---

## 👥 Integrantes

| Nome | RM |
|------|------|
| 🧑‍💻 Breno Silva | 99275 |
| 👨‍💻 Danilo Urze | 99465 |
| 👨‍💻 Henrique Lima | 551528 |

---

## 🌱 ODS da ONU

### 🎓 **ODS 4 – Educação de Qualidade**
Promove educação acessível por meio de trilhas contínuas de aprendizado.

### 💼 **ODS 8 – Trabalho Decente e Crescimento Econômico**
Inclui monitoramento do bem-estar (`statusBemEstar`) para evitar burnout.

### 🏗️ **ODS 9 – Inovação e Infraestrutura**
Estrutura moderna em API, pronta para escalar.

### 🤝 **ODS 10 – Redução das Desigualdades**
Democratiza o acesso a habilidades de alta demanda no mercado.

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java 17**
- 🍃 **Spring Boot 3.x**
- 🔐 **Spring Security + JWT**
- 🛢️ **Oracle Database**
- 📦 **Spring Data JPA**
- ✔️ **Jakarta Bean Validation**
- 🧪 **Postman**
- 🔧 **Maven**

---

## ⚙️ Configuração do Ambiente

### 🔧 Pré-requisitos
- JDK 17
- Maven
- Banco Oracle

---

### 🗄️ Configuração do Banco (`application.properties`)

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RM99275   # seu usuário
spring.datasource.password=SUA_SENHA # sua senha
spring.jpa.hibernate.ddl-auto=update

jwt.secret=SUA_CHAVE_SECRETA_JWT_BASE64
```

---

### ▶️ Como executar

```bash
mvn clean install
mvn spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

---

## Está disponibilizado junto com a nossa api um Export com todas as requisições caso queira importar para o Postman:
- (WorkMind SkillQuest API.postman_collection)



# 🔌 Endpoints

## 🔐 1. Autenticação

### ➕ Criar Usuário — **POST /usuarios**
```json
{
  "nome": "Usuario de Teste",
  "email": "teste@workmind.com",
  "senha": "123",
  "areaAtuacao": "Testes",
  "nivelCarreira": "JUNIOR",
  "pontosXP": 0,
  "statusBemEstar": "OK"
}
```

### 🔑 Login — **POST /usuarios/login**
```json
{
  "email": "teste@workmind.com",
  "senha": "123"
}
```

**Retorno:**
```json
{
  "token": "TOKEN_JWT_AQUI"
}
```

---

## 👤 2. Usuários

### 📄 Listar Usuários — **GET /usuarios**
🔒 Requer Token

### 🏆 Ranking — **GET /usuarios/ranking**
Ordenado por maior XP.

### 🤖 IA Mock: Sugerir Missão — **GET /usuarios/{id}/sugerir-missao**

### ✏️ Atualizar Usuário — **PUT /usuarios/{id}**
```json
{
  "nome": "Diego Developer (Promovido)",
  "email": "diego.dev@workmind.com",
  "areaAtuacao": "DevOps",
  "nivelCarreira": "PLENO",
  "pontosXP": 1200,
  "statusBemEstar": "OK"
}
```

---

## 🎯 3. Trilhas de Aprendizagem

### ➕ Criar Trilha — **POST /trilhas**
```json
{
  "nome": "Fundamentos de Cloud (AWS)",
  "descricao": "Curso introdutório sobre AWS S3 e EC2.",
  "nivel": "INICIANTE",
  "cargaHoraria": 15,
  "focoPrincipal": "Cloud Computing",
  "recompensaXP": 500
}
```

### 🔍 Buscar por Foco — **GET /trilhas/buscar?foco=Inteligencia**

---

## 🧪 Como Testar no Postman

1. Importe a coleção **WorkMind SkillQuest API.postman_collection.json**
2. Crie um usuário
3. Faça login
4. Copie o JWT token retornado
5. Em cada requisição:
    - Authorization → **Bearer Token**
    - Cole o token
6. Teste à vontade 🚀

---

## 📄 Licença

Projeto criado para **FIAP – Global Solution 2025**.  
Uso livre para fins educacionais e acadêmicos.

