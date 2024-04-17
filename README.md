# customer-backend-challenge

Trata-se de um projeto que consome uma API de proutos da Magazine Luiza e adiciona itens numa lista de favoritos de respectivo usuario.

Requisitos para rodar o projeto:

    - Java 21
    - Sistema gerenciador de banco de dados que trabalhe com Postgres (utilizamos o DBeaver)
    - Postman ou Insomnia para teste das requisicoes

Passos para utilizar a aplicacao:

    - Abra a aplicacao na sua IDE, instale as dependencias;
    - Crie o banco de dados com o nome customerbackendchallengedb e configure a conexao de acordo com os seguintes dados:
        - url= postgresql://localhost:5432/customerbackendchallengedb
        - username= postgres
        - password= 1234
    - Execute a aplicacao

No Postman, faca os testes. Seguem alguns exemplos de requisicao:
       
#### criacao de um customer: 
            curl --location 'http://localhost:8080/api/customers' \
            --header 'Content-Type: application/json' \
            --data-raw '{
                "name": "Joao",
                "email": "joao.silva@example.com",
                "favorites": []
            }'

#### listar todos os customer:
            curl --location 'http://localhost:8080/api/customers' \
            --data ''

#### buscar um customer por id
            curl --location 'http://localhost:8080/api/customers/1' \
            --data ''

#### buscar a lista de produtos
            curl --location 'http://localhost:8080/api/products/?page=1' \
            --data ''

#### buscar detalhes de um produto
            curl --location 'http://localhost:8080/api/products/4bd442b1-4a7d-2475-be97-a7b22a08a024' \
            --data ''

#### adicionar um item favorito na lista do usuario criado acima
            curl --location --request POST 'http://localhost:8080/api/customers/1/favorites/4bd442b1-4a7d-2475-be97-a7b22a08a024' \
            --data ''