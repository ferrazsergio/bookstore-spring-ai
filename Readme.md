# Projeto AI Bookstore Assistant

Este é um projeto de exemplo de uma assistente virtual para livrarias, desenvolvido utilizando Spring Boot em conjunto com Spring AI e OpenAI.

## Visão Geral

Este projeto consiste em uma API RESTful que permite aos usuários interagir com uma assistente virtual para obter informações sobre livros, incluindo recomendações de best sellers, resumos de livros e biografias de autores.

## Funcionalidades Principais

- Consulta de informações sobre livros de TI em alta ultimamente.
- Consulta de resenhas de livros.
- Stream de informações sobre livros.

## Tecnologias Utilizadas

- Spring Boot
- Spring AI
- OpenAI
- Reactor Core 
- Java 21

## Estrutura do Projeto

O projeto é dividido em duas partes principais: Controllers e Services.

### Controllers

- `BookStoreAssistantController`: Responsável por expor endpoints HTTP para interação com o assistente virtual.

### Services

- `BookStoreService`: Responsável por realizar consultas assíncronas ao OpenAI para obter informações sobre livros e revisões.

## Como Executar o Projeto

1. Certifique-se de ter o Java 21 instalado em sua máquina.
2. Clone este repositório.
3. Abra o projeto em sua IDE favorita.
4. Obtenha sua API Key da OpenAI em [https://openai.com/](https://openai.com/).
5. Configure sua API Key do OpenAI no arquivo `application.properties`.
6. Execute a classe principal `AiApplication`.

> **Nota:** É importante obter sua própria API Key da OpenAI para testar este projeto.

## Endpoints da API

### Consulta de Informações sobre Livros Best Sellers

- **URL:** `/bookstore/informations`
- **Método:** GET
- **Descrição:** Este endpoint retorna informações sobre livros best sellers de forma síncrona. Isso significa que ele espera a resposta completa da assistente virtual antes de enviar uma resposta de volta para o cliente. A solicitação é processada e a resposta é enviada imediatamente após o processamento.
- **Parâmetros:**
  - `message`: (Opcional) A mensagem para a assistente virtual.
- **Exemplo de Uso:**
  ```bash
  curl -X GET "http://localhost:8080/bookstore/informations?message=Quais são os livros de TI que estão em alta ultimamente?

### Solicitação de Resumos de Livros e Biografias de Autores

- **URL:** `/bookstore/reviews`
- **Método:** GET
- **Parâmetros:**
  - `book`: (Opcional) O nome do livro para o qual deseja obter informações..
- **Exemplo de Uso:**
  ```bash
  curl -X GET "http://localhost:8080/bookstore/reviews?book=Código Limpo"

### Streaming de Conversas com a Assistente Virtual

- **URL:** `/bookstore/stream/informations`
- **Método:** GET
- **Descrição:** Este endpoint retorna informações sobre livros de forma assíncrona, em um fluxo contínuo. Isso permite uma interação em tempo real com a assistente virtual. Em vez de esperar pela resposta completa antes de enviar uma resposta de volta, este endpoint envia partes da resposta conforme elas são disponibilizadas pela assistente virtual, permitindo uma resposta incremental.
- **Parâmetros:**
  - `message`: (Opcional) A mensagem para a assistente virtual.
- **Exemplo de Uso:**
  ```bash
  curl -X GET "http://localhost:8080/bookstore/stream/informations?message=Quais são os livros best sellers dos ultimos anos?"

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver alguma sugestão de melhoria, sinta-se à vontade para abrir uma issue ou enviar um pull request.
