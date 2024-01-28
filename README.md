# Projeto de Gestão de Avaliações - Documentação

Este é um projeto de demonstração para uma aplicação de gestão de avaliações desenvolvida com Spring Boot.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java Development Kit (JDK) 17 ou superior
- Maven 3.6.0 ou superior
- Docker
- Docker Compose

## Configuração do Banco de Dados

A aplicação utiliza um banco de dados PostgreSQL. Você pode configurar o banco de dados manualmente ou usar o Docker Compose para iniciar um contêiner do PostgreSQL.

### Configuração Manual do Banco de Dados

1. Instale o PostgreSQL em sua máquina.
2. Crie um banco de dados com o nome `gestao_avaliacao_db`.
3. Configure as credenciais de acesso ao banco de dados no arquivo `application.properties` do projeto Spring Boot.

### Configuração usando Docker Compose

1. Clone o repositório.
2. Navegue até o diretório raiz do projeto.
3. Execute o seguinte comando para iniciar o banco de dados PostgreSQL:

```bash
docker-compose up -d
