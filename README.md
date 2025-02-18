# Registro de Ponto

## Descrição:
O **RegistroPonto** é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. Seu objetivo é gerenciar o registro de ponto dos funcionários de uma empresa, permitindo o controle eficiente das horas trabalhadas.

## Tecnologias Utilizadas:

- Java
- Spring Boot
- Banco de Dados (especifique qual está utilizando, por exemplo, MySQL, PostgreSQL)
- Docker (se aplicável)
- Maven (para gerenciamento de dependências)

## Funcionalidades:
- Registro de Usuários: Registra usuários de acordo com
- Login de Usuários: Faz login de usuários registrados com token JWT
- Bater Ponto: Requisição para registrar o horário da batida de ponto
- Histórico do Funcionário: Mostra o histórico de batidas de uma determinada data
- Gerar relátorios dos funcionários: Gera um relatório com o total de horas trabalhas e as horas de cada funcionário
- Histórico de Funcionários: Mostra o histórico de todos os funcionários em uam determinada data

## EndPoints
### Autenticação:
```
POST /user/auth/register - Registra novo usuário
POST /user/auth/login - Login com token JWT
```
### Funcionários:
```
POST /check/employee/ - Batidade de ponto
GET /check/employee/punch-clock/history - Busca batidas de ponto por data
```
### Administração:
```
GET /admin/get - Busca batidas de ponto dos funcionários
GET /admin/reports - Gera relátorio de pontos 
```
## Como Executar:
1. Clone o repositório
2. Buildar o docker-compose
```
docker-compose build
```
3. Rodar o projeto
```
docker-compose up -d
```
## Requisitos:
- Docker



