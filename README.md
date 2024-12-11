# DesafioMV

## Descrição
Este projeto é uma aplicação Java desenvolvida utilizando o framework Spring Boot. O objetivo do projeto é demonstrar o uso de várias tecnologias e práticas de desenvolvimento.

## Tecnologias Utilizadas
- **Java 21**: Linguagem de programação utilizada.
- **Spring Boot 3.4.0**: Framework para facilitar o desenvolvimento de aplicações Java.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **Oracle Database**: Banco de dados utilizado na aplicação.

## Estrutura do Projeto
- **pom.xml**: Configurações do Maven e dependências do projeto.
- **src/main/resources/application.properties**: Configurações da aplicação, incluindo detalhes de conexão com o banco de dados.
- **src/main/java/com/desafiomv**: Diretório principal contendo o código fonte da aplicação.

## Configurações Importantes
As principais configurações da aplicação estão no arquivo `application.properties`:
```ini
spring.application.name=desafioMV

spring.datasource.url=jdbc:oracle:thin:@localhost:1524:xe
spring.datasource.username=SYSTEM
spring.datasource.password=35795100@Jck
spring.jpa.hibernate.ddl-auto= create-drop
spring.datasource.driver.class-name=oracle.jdbc.OracleDriver

url.api=http://localhost:8080/
banco.username=SYSTEM
banco.senha=35795100@Jck
