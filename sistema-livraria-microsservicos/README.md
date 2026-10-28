# Sistema de Livraria com Microsserviços

Projeto acadêmico inspirado nos slides de Sistemas Distribuídos – Microsserviços Parte II. Cada serviço é uma aplicação Spring Boot independente, possui banco MySQL próprio e scripts Flyway para criar e popular tabelas.

## Serviços e portas
- `cambio-service` – `http://localhost:8000`; banco `cambio_service`.
- `book-service` – `http://localhost:8100`; banco `book_service`. Contém `tb_categoria` e `tb_book`, relacionadas por chave estrangeira.
- `autor-service` – `http://localhost:8200`; banco `autor_service`.

## Requisitos
- Java 21 ou compatível com Spring Boot 3.5.8
- Maven 3.9+ (ou Maven Wrapper, caso adicionado pelo ambiente)
- MySQL 8+

## Preparar os bancos
Crie os três bancos no MySQL:
```sql
CREATE DATABASE cambio_service;
CREATE DATABASE book_service;
CREATE DATABASE autor_service;
```
Configure usuário e senha em `application.properties` de cada serviço. O padrão é `root` / `admin123`, conforme o exemplo dos slides. Flyway cria e popula as tabelas ao iniciar cada serviço.

## Executar
Abra três terminais, na raiz de cada pasta, e execute:
```bash
mvn spring-boot:run
```
Inicie `cambio-service` antes de `book-service`, pois o serviço de livros consulta o serviço de câmbio. O `autor-service` pode ser iniciado independentemente.

## Exemplos de teste (Postman ou navegador)
- `GET http://localhost:8000/cambio-service/19.99/USD/BRL`
- `GET http://localhost:8100/book-service/1/BRL`
- `GET http://localhost:8100/book-service`
- `GET http://localhost:8100/categoria`
- `GET http://localhost:8200/autor`
- `POST http://localhost:8200/autor` com JSON `{ "nome": "Machado de Assis", "nacionalidade": "Brasileira" }`
- `POST http://localhost:8100/categoria` com JSON `{ "nome": "Romance", "descricao": "Romances e literatura" }`
- `POST http://localhost:8100/book` com JSON `{ "author": "Machado de Assis", "title": "Dom Casmurro", "launchDate": "1899-01-01", "price": 19.99, "categoriaId": 1 }`

## Estrutura e tratamento de erros
Cada serviço possui pacotes `controller`, `model`, `repository`, `service`, `exception` e `handle`. O pacote `handle` contém um `GlobalExceptionHandler` para respostas HTTP de erro. As migrações de banco ficam em `src/main/resources/db/migration`.

## GitHub
Envie a pasta `micro-livraria` para um repositório GitHub. O projeto inclui código e scripts versionados para criação e população dos bancos. Não contém senhas reais; altere as credenciais para seu ambiente.
