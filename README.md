# FIAP - Credcard
## Desafio - Avaliação - Spring Framework

A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos
alunos e para isso necessita de um sistema para gerenciamento e
integração com outras empresas

## card-web <img align="left" alt="Spring" width="30px" src="https://devkico.itexto.com.br/wp-content/uploads/2014/08/spring-boot-project-logo.png" /> 
- A aplicação "card-web" tem como objetivo ser autorizadora, que irá receber as transações dos clientes/alunos da FIAP. Para o funcionamento da aplicação deve-se usar o Java 17 e foi usado o Gradle como gerenciador de dependências. Rodar a aplicação local e consumir os endpoints exposto no Swagger. A instância do banco de dados ficará nesse projeto e será o H2.
Tecnlogias: Java 17, Spring Framewok, H2 Database, Rest API.

## card-batch <img align="left" alt="RabbitMQ" width="40px" src="https://img.stackshare.io/service/9201/dbefbe0f6d93161f545994d3aff87775.png"/>
- A aplicação "card-batch" tem a responsabilidade de processar um arquivo que será disponibilizado para processar e guardar as informações de potenciais clientes. A instância do banco de dados usada para salvar os dados será o H2 outra aplicação "card-web". Essa batch será responsável por processar e salvar a malha de dados referentes as entidades ESTUDANTES, CARTOES e TRANSACOES.
É importante então, antes subir no ambiente primeiro a aplicação "card-web".

## H2 Database <img align="left" alt="Java" width="60px" src="https://www.h2database.com/html/images/h2-logo-2.png" />
- Foi escolhido o banco de dados H2, um detalhe importante é que a instância do mesmo estará dentro da aplicação "card-web". Não está sendo salvo em memória e sim em um arquivo dentro da pasta home do usuário da máquina local com o nome "fiap-card".
O arquivo do banco de dados H2 ficará no caminho local: ````~/h2database/fiap-card.</div>```` O caractere "`~`" leva a pasta do usuário atual seguindo do diretorio h2database arquvios "fiap-card*".

## Postman <img align="left" alt="Spring" width="30px" src="https://seeklogo.com/images/P/postman-logo-F43375A2EB-seeklogo.com.png" />
- Foi disponibilizado uma collection Postman para facilitar o consumo das APIs.

![image](https://user-images.githubusercontent.com/45438870/193468788-88c5432c-0211-433d-af2e-55ab8b4c443e.png)


## Swagger <img align="left" alt="Rest" width="30px" src="https://icon-library.com/images/rest-api-icon/rest-api-icon-1.jpg" />
- Documentação Swagger para exibição dos endpoint da aplicação "card-web".
Endereço: http://localhost:8081/api/swagger-ui.html#/
Atenção para os dois endpoints no controller /transactions com /download no final do endpoint, esse são os endpoints de download de arquivo conforme exposto no desafio.

![image](https://user-images.githubusercontent.com/45438870/193468735-b2b9b4d2-62b4-4f5e-927b-014a2d395664.png)



## Como rodar as aplicações:
````
Primerio é necessário subir a aplicação "card-web" e quando estiver rodando, não usar nenhum recurso exposto nessa API, isso será explicado o porque abaixo.
Depois rodar a aplicação "card-batch". Essa aplicação irá inserir no banco de dados os potenciais clientes do arquivo exposto pelo professor no desafio, essa massa do arquivo representa os ESTUDANTES. 
Como foi solicitado para que nós fizessemos uma massa de simulção da trânsferências, a aplicação "card-batch" também irá inserir baseado em outros dois arquivos uma massa que representa os CARTOES desse aluno e outra massa que representa as TRANSACOES desse aluno e desse cartão. Por isso que é importante não usar os recursos da aplicação "card-web" antes de rodar a aplicação "card-batch". Em um cenário real em produção, essa simulação/migração de banco de dados de transferências também se aplica, ou seja é necessário primeiro essa migração antes de executar a operção do serviço.
Feito isso consumir os endpoints da aplicação "card-web". 

````

