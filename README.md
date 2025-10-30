# 💰 Financial Manager API

[![Licença](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Linguagem Principal](https://img.shields.io/badge/Language-Java-brightgreen.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-Spring%20Boot-green.svg)](https://spring.io/projects/spring-boot)

## 📖 Descrição do Projeto

O **Financial Manager API** é uma API RESTful robusta e eficiente, desenvolvida para ser o *backend* de uma aplicação de gestão financeira pessoal ou empresarial.

O objetivo principal é fornecer uma plataforma centralizada para o gerenciamento de dados financeiros, incluindo o registro, consulta, atualização e exclusão de receitas, despesas, categorias e contas de usuário, garantindo segurança e escalabilidade.

## ✨ Funcionalidades

* **Autenticação e Autorização:** Registro de novos usuários e login seguro (usando JWT, por exemplo).
* **Gestão de Contas:** Criação e gerenciamento de contas financeiras (carteira, banco, investimento).
* **Transações (CRUD):** Registro completo de receitas e despesas com data, descrição, valor e conta associada.
* **Categorização:** Criação e atribuição de categorias para um melhor controle financeiro.
* **Relatórios:** Endpoints para consultar saldos e extratos por período, conta ou categoria.

## 🛠️ Tecnologias Utilizadas

A API foi desenvolvida utilizando a seguinte *stack* de tecnologia:

| Categoria | Tecnologia |
| :--- | :--- |
| **Linguagem** | Java |
| **Framework** | Spring Boot |
| **Gerenciador de Dependências** | Maven / Gradle (substitua pelo que você usa) |
| **Banco de Dados** | PostgreSQL / MySQL / H2 (substitua pelo que você usa) |
| **Mapeamento Objeto-Relacional** | Spring Data JPA / Hibernate |
| **Segurança** | Spring Security |

## ⚙️ Pré-requisitos

Para rodar este projeto localmente, você precisará ter instalado:

* **Java Development Kit (JDK):** Versão 17+ (ou a versão que você usou)
* **Maven** ou **Gradle**
* Um SGBD como **PostgreSQL** ou **MySQL** (e suas credenciais de acesso)
* Uma IDE de sua preferência (IntelliJ IDEA, VS Code, Eclipse, etc.)

## 🚀 Instalação e Configuração

Siga os passos abaixo para configurar e executar a API em seu ambiente local:

### 1. Clone o Repositório

```bash
git clone [https://github.com/salesleozz/financial-manager-api.git](https://github.com/salesleozz/financial-manager-api.git)
cd financial-manager-api
