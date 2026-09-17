# Guardian

Sistema web para gerenciamento e proteção de dispositivos móveis, desenvolvido em Java utilizando arquitetura MVC.

O Guardian permite o cadastro e autenticação de usuários, gerenciamento de informações pessoais e controle dos dispositivos vinculados a cada conta. A aplicação também possui recursos de proteção simulados, como bloqueio e localização do aparelho.

## Funcionalidades

### Usuários

* Cadastro de usuários
* Login e logout
* Controle de sessão
* Visualização e edição do perfil
* Exclusão da conta
* Restrição de acesso para usuários não autenticados

### Dispositivos

* Cadastro de celulares
* Edição e exclusão de aparelhos
* Associação automática do celular ao usuário autenticado
* Controle de status do dispositivo
* Visualização apenas dos aparelhos pertencentes ao usuário
* Simulação das funções de bloqueio e localização

### Regras de negócio

O sistema possui uma validação para impedir que um usuário mantenha mais de um aparelho com status `ATIVO` ao mesmo tempo.

A associação entre usuário e aparelho também é realizada automaticamente através da sessão, evitando que o `usuario_id` seja informado manualmente pelo formulário.

## Tecnologias

* **Java 17**
* **Jakarta Servlet**
* **JSP**
* **HTML5**
* **CSS3**
* **JDBC**
* **MySQL**
* **Apache Tomcat 10.1**
* **Maven**
* **Docker**

## Arquitetura

A aplicação segue o padrão **MVC**, com separação das responsabilidades entre as principais camadas:

```text
Model
 └── Usuario
 └── Celular

View
 └── JSP

Controller
 └── UsuarioController
 └── CelularController

Service
 └── UsuarioService
 └── CelularService

DAO
 └── UsuarioDAO
 └── CelularDAO
```

O fluxo básico de uma operação segue:

```text
JSP
 ↓
Servlet / Controller
 ↓
Service
 ↓
DAO
 ↓
MySQL
```

A camada `Service` concentra as regras de negócio, enquanto os `DAO` são responsáveis pelas operações no banco de dados.

## Banco de dados

O Guardian utiliza MySQL e possui duas entidades principais:

### usuarios

| Campo | Tipo    | Descrição                |
| ----- | ------- | ------------------------ |
| id    | INT     | Identificador do usuário |
| nome  | VARCHAR | Nome do usuário          |
| email | VARCHAR | E-mail para autenticação |
| senha | VARCHAR | Senha da conta           |

### celulares

| Campo          | Tipo    | Descrição                 |
| -------------- | ------- | ------------------------- |
| id             | INT     | Identificador do aparelho |
| marca          | VARCHAR | Marca do celular          |
| modelo         | VARCHAR | Modelo do celular         |
| IMEI           | VARCHAR | Identificação do aparelho |
| status_celular | VARCHAR | Status do dispositivo     |
| usuario_id     | INT     | Usuário associado         |

Relacionamento:

```text
usuarios 1 ───────── N celulares
```

## Estrutura

```text
src/
└── main/
    ├── java/
    │   ├── controller/
    │   ├── dao/
    │   ├── filter/
    │   ├── model/
    │   ├── service/
    │   ├── servlet/
    │   └── util/
    │
    └── webapp/
        ├── css/
        ├── cadastro.jsp
        ├── celulares.jsp
        ├── inicio.jsp
        ├── login.jsp
        ├── usuarios.jsp
        └── WEB-INF/
            └── web.xml

pom.xml
README.md
```

## Requisitos

Para executar o projeto, é necessário ter instalado:

* JDK 17
* Maven
* Docker
* Apache Tomcat 10.1.x
* MySQL
* Eclipse ou outra IDE Java

## Configuração

O banco utilizado pela aplicação é:

```text
guardian
```

A conexão utilizada durante o desenvolvimento é feita através do container Docker:

```text
jdbc:mysql://mysql-guardian:3306/guardian
```

As configurações de acesso ao banco podem ser encontradas em:

```text
src/main/java/util/ConnectionFactory.java
```

Antes de executar a aplicação, o banco e as tabelas devem estar disponíveis no MySQL.

## Execução

Clone o repositório:

```bash
git clone <(https://github.com/LucasBorgesSousa/Guardian)>
```

Entre no diretório:

```bash
cd Guardian
```

Compile o projeto:

```bash
mvn clean package
```

Em seguida, execute a aplicação utilizando o Apache Tomcat 10.1.

Com o servidor iniciado, acesse:

```text
http://localhost:8080/guardian/login
```

## Autenticação

As páginas principais da aplicação são protegidas por sessão.

O `AuthFilter` verifica se existe um usuário autenticado antes de permitir o acesso às seguintes rotas:

```text
/inicio
/usuario
/celular
```

Caso não exista uma sessão válida, o usuário é redirecionado para a página de login.

Além disso, as operações relacionadas a usuários e celulares verificam o proprietário do registro antes de permitir alterações ou exclusões.

## Recursos de proteção

O Guardian possui duas funcionalidades voltadas à proposta de segurança do sistema:

**Travar aparelho**

Simula o envio de um comando para bloquear o dispositivo.

**Localizar aparelho**

Simula uma solicitação de localização do dispositivo.

Esses recursos são demonstrativos e não realizam bloqueio ou geolocalização real.

## Objetivo

O projeto foi desenvolvido no contexto acadêmico para aplicação prática de conceitos de desenvolvimento web com Java, arquitetura MVC, persistência de dados, autenticação, controle de sessão e regras de negócio.

## Desenvolvedores

**Lucas Borges de Sousa**
**Heitor Santiago Silveira Soprani Toledo**
**Vinícius Henrique de Oliveira Melo Silva**

## Status

Projeto acadêmico em desenvolvimento.
