# Customer backend Challenge

Trata-se de um projeto que consome uma API de proutos da Magazine Luiza e adiciona itens numa lista de favoritos de respectivo usuario. Foram usadas as seguintes tecnologias:
- Java 21
- Spring Boot 3.2.4
- Postgres

Requisitos para rodar o projeto:
- Java 21
- Maven
- Docker
- Postman ou Insomnia para teste das requisições
- DBeaver ou outro gerenciador de banco de dados caso você queira acompanhar o comportamento do banco

Passos para utilizar a aplicacao:

- Faça o clone do repositório:

        git clone https://github.com/renatoganske/customer-backend-challenge.git
    
- Abra um terminal na pasta do projeto e execute o comando

        docker-compose up

A aplicação deverá subir e você poderá realizar os testes no Postman. Seguem alguns exemplos de requisição:
       
#### Criação de um customer: 

            curl --location 'http://localhost:8080/api/customers' \
            --header 'Content-Type: application/json' \
            --data-raw '{
                "name": "Joao",
                "email": "joao.silva@example.com",
                "favorites": []
            }'

#### Listar todos os customer:

            curl --location 'http://localhost:8080/api/customers' \
            --data ''

#### Buscar um customer por id

            curl --location 'http://localhost:8080/api/customers/1' \
            --data ''

#### Buscar a lista de produtos

            curl --location 'http://localhost:8080/api/products/?page=1' \
            --data ''

#### Buscar detalhes de um produto

            curl --location 'http://localhost:8080/api/products/4bd442b1-4a7d-2475-be97-a7b22a08a024' \
            --data ''

#### Adicionar um item favorito na lista do usuario criado acima

            curl --location --request POST 'http://localhost:8080/api/customers/1/favorites/4bd442b1-4a7d-2475-be97-a7b22a08a024' \
            --data ''
