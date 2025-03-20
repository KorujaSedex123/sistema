# 🚀 Sistema de Agendamento de Reuniões (Backend)  

## Descrição do Projeto
Este é o Backend de um sistema para agendamento de reuniões, desenvolvido com SpringBot. Ele permite que os
usuários agendem,  visualizem e gerenciem reuniões de forma eficiente.

## 🛠️ Tecnologias Utilizadas
- **Spring Boot**: – Framework principal do back-end
- **Spring Security** – Segurança e autenticação
- **Spring Data JPA** – Gerenciamento do banco de dados
- **PostgreSQL** – Banco de dados relacional
- **Lombok** – Redução de boilerplate no código
- **JWT** – Autenticação via token (se aplicável)

🚀 Instalação e Execução

## Pré-requisitos
- Java 17 ou superior
- Maven instalado
- Banco de Dados PostgreSQL (ou outro configurado no projeto)


## 🛠 Configuração do Banco de Dados
- Crie um banco de dados no PostgreSQL.
- Configure o arquivo application.properties ou application.yml em src/main/resources/, informando as credenciais do banco.

## 🔑 Autenticação e Segurança
O sistema utiliza Spring Security para autenticação e autorização. Caso JWT seja utilizado, os tokens devem ser enviados no cabeçalho das requisições.


