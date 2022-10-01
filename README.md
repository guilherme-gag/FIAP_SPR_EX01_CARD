# FIAP - Credcard

## Desafio - Avaliação - Spring Framework
A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos
alunos e para isso necessita de um sistema para gerenciamento e
integração com outras empresas

## card-web <img align="left" alt="Spring" width="30px" src="https://devkico.itexto.com.br/wp-content/uploads/2014/08/spring-boot-project-logo.png" /> <img align="left" alt="Rest" width="30px" src="https://icon-library.com/images/rest-api-icon/rest-api-icon-1.jpg" />
- A aplicação "card-web" tem como objetivo ser autorizadora, que irá receber as transações dos clientes/alunos da FIAP. Para o funcionamento da aplicação deve-se usar o Java 17 e foi usado o Gradle como gerenciador de dependências. Rodar a aplicação local e consumir os endpoints exposto no Swagger. A instância do banco de dados será usado nesse projeto e será o H2.
Tecnlogias: Java 17, Spring Framewok, H2 Database, Rest API.

## card-batch <img align="left" alt="RabbitMQ" width="40px" src="https://img.stackshare.io/service/9201/dbefbe0f6d93161f545994d3aff87775.png"/>
- A aplicação "card-batch" tem a responsabilidade de processar um arquivo que será disponibilizado para processar e guardar as informações de potenciais clientes. A instância usada para salvar os dados será o H2 da aplicação "card-web". É importante então, antes subir no ambiente primeiro a aplicação "card-web".

## H2 Database <img align="left" alt="Java" width="60px" src="https://www.h2database.com/html/images/h2-logo-2.png" />
- Foi escolhido o banco de dados H2, um detalhe importante é que a instância do mesmo estará dentro da aplicação "card-web". Não está sendo salvo em memória e sim em um arquivo dentro da pasta home do usuário da máquina local com o nome "fiap-card".

## Postman <img align="left" alt="Spring" width="30px" src="https://seeklogo.com/images/P/postman-logo-F43375A2EB-seeklogo.com.png" />
- Foi disponibilizado uma collection Postman para facilitar o consumo das APIs.

````
spring.mail.host=smtp.office365.com #VERIFICAR O SMTP DO PROVEDOR DE E-MAIL ex: GMAIL: smtp.gmail.com, OUTLOOK: smtp.office365.com
spring.mail.port=587 #VERIFICAR PORTA DISPONIVEL DO PROVEDOR DE E-MAIL
spring.mail.password=senhaDoEmail #SENHA
spring.mail.username=emailQueVaiEnviar@email.com #EMAIL
````

- Esse projeto ao fazer a leitura do broker, vai processar os dados e idenfiticar conforme o desafio quando terá que enviar um e-mail de alerta ao e-mail cadastrado.
