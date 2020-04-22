# Assembleia Votação

Projeto simples para simular a votação de Sessões durante uma Assembleia.
Permitido o cadastro de Pautas, Associados e Sessões.

### Tecnologias

Projeto Java 8 criado com Maven. Foi desenvolvido utilizando os frameworks Spring Boot e a arquitetura REST.

Banco de dados utilizado é o MySQL, com o schema e tabelas montados com a ajuda do Liquibase. Para criação do mesmo, devido ao tempo de desenvolvimento, infelizmente não foi utilizado o Docker, sendo necessário ter o MySQL instalado na máquina para testes.

Foi utilizado a biblioteca do Lombok apenas para facilitar na escrita do código.

Os testes unitários foram gerados com o JUnit em conjunto Mockito.

## Arquitetura

Foi montada uma arquitetura simples de um projeto monolítico apenas para atender a proposta do desafio.
Há uma camada <b>domain</b> que contém os objetos de domínio para persistir no banco de dados. Essa camada contém além das entidades, a camada <b>infrastructure</b> que contém os repositórios de conexões com o banco de dados.
Uma camada <b>service</b> que contém as classes de serviços e <b>business</b> que contém as regrasde negócio da aplicação.
Na camada <b>rest</b> fica os recursos de entrada do sistema, ou seja, as classes "Controllers" e os objetos de requisição.
Ainda há uma camada com as Exceções possíveis lançadas.

### Executar

Para iniciar o jogo, basta apenas executar a classe <b>Application</b> que contém o método <b>main</b>.
Aplicação disponibilizada na <b>URL: localhost:8080/assembleia </b>.

### Operações

## Associado
 
# Criar

POST http://localhost:8080/assembleia/associado

Exemplo de Request:

{
    "nome" : "Marcelo Formiga"
}

# Atualizar

PUT http://localhost:8080/assembleia/associado

Exemplo de Request:

{
    "id" : 1,
    "nome" : "Marcelo"
}

# Listar todos

GET http://localhost:8080/assembleia/associado

# Recuperar por ID

GET http://localhost:8080/assembleia/associado/1


## Pauta
 
# Criar

POST http://localhost:8080/assembleia/pauta

Exemplo de Request:

{
    "descricao" : "Reformas"
}

# Atualizar

PUT http://localhost:8080/assembleia/pauta

Exemplo de Request:

{
    "id" : 1,
    "descricao" : "Reformas do Salão"
}

# Listar todos

GET http://localhost:8080/assembleia/pauta

# Recuperar por ID

GET http://localhost:8080/assembleia/pauta/1


## Sessão
 
# Criar

POST http://localhost:8080/assembleia/sessao

Exemplo de Request:

{
    "pautaId" : "4",
    "tempoEmMinutos" : "60"
}

# Votar

POST http://localhost:8080/assembleia/sessao/votar

Exemplo de Request:

{
    "sessaoId" : 1,
    "associadoId" : 4,
    "voto" : "NAO"
}