# vehicles-sales-api

## 📫 Tecnologias e Libs Utilizadas

1. Spring boot
2. Spring Security
3. JWT
4. Lombok
5. MongoDB
6. Validation

## Banco de Dados

Utilizei MongoDB para aperfeiçoar meus conhecimento em bancos não relacionais,
o arquivo `aplication.properties` foi configurado a porta que será utilizada tanto para o meu servidor : `8086`
e a porta para o banco : `27017`.

## Package Entities

Foi definida duas Classes que irá compor o projeto, Vehicles e User
respectivamente, foi definido seus atributos de acordo com os requisitos

Na classe Vehicles, foi utilizado Anotoções do Lombok para gerar Getters e Setters e Construtores,
Juntamante da Anotação do Mongo, que criará um Documento conforme os atributos da classe.

Na classe User, um dos atribuitos que compõe é Roles, que será um enum tendo as opçoes de ser `ADMIN` ou `USER`

## Package Repository

Foi atribuído 2 Repositórios que persistirá com Banco de Dados

`VehicleRepository` é uma interface que extends uma Classe MongoRepository que contém todos os métodos padrões de crud e persitência de dados,
sem a necessidade de criar Querys para selects. 

`UserRepository` é uma classe que contém usúarios já cadastrados, possivelmente pode ser substuído por um banco de dados 

## Packge Service

`TokenService` será a classe responsável para gerar os meus Tokens JWT, vincular a Claim de Role e também o Subject com o id do  Usúario,
para posteriamente ter acesso a estes dados ao decodificar o Token. 

`UserService` será responsavél para logar o meu usuário,
Ao pegar as credencias do mesmo, será feita uma busca no repositório de usuarios comparando as informações e posteriomente gerando e `retornando` o TOKEN

`CustomDetailService` para a necessidade de uma de autenticação personalizada a classe CustomDetailService tem como finalidade de criar um `UserPrincipal` 
passando parametros `User` o `UserPrincipal` que de fato será autenticado no processo. 

`VehicleService` responsavél por fazer o CRUD com o repositório.

## Package Controller

`UserControllers` terá dois endpoints `/login` que será utilizado o método POST no qual será passado um novo usuário e então será logado a partir da utilização do UserService
e o outro endpoint `/` é responsavél por retornar todos os usuarios cadastrados com o método GET. 
`O primeiro endpoint é uma rota pública, não precisa do usuário estar autenticado diferentemente da segunda, segue abaixo explicacao da configuração`
Nota-se a anotação de Pre-Authorização, a condição para acessar a rota do GET é necessário o úsuario ter a Role de Admin

`VehicleController` possui 5 endpoints nos quais apenas 1 é de acesso público que no caso retorna a lista de Veiculos, 
todos os demais possui a restrição de Admin, sendo PUT, EDIT, POST, DELETE os métodos para lidar com os veiculos . 

## Package Auth 
`Authentication Filter`, durante as requisições http, será necessário a passagem de um filtro para autenticar o usúario e validar o token JWT, essa classe é responsavél por isso,
caso o token seja criado, a autenticação é feita instanciando um UserDetails

`UserPrincipal` implementa os métodos do UserDetails, utilizada no CustomDetail service. 

## Package Config
` WebSecurity` nesta classe é configurado a autorização e o filtro que será utilizado para autenticar um usuário
no código foi configurado para todos as requests de `/api/login` ou `/api/veiculos`terão acesso público e as demais rotas para o acesso, o usuário deverá ser autenticado.

## Funcionamento

Para o funcionamento da Api é necessário possuir mongoDB na máquina, posteriormente verificar se as portas não estão sendo utilizadas.
Além disso, ter java 11 instalado na máquina
