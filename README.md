# moc-calculator
 Projeto que disponibiliza funcionalidades básicas de uma calculadora através de API Rest. Com dois módulos, REST e CORE, onde a comunicação é realizada através do Spring AMQP.

## Tecnologias e Características
1. Java 18
2. Springboot
3. RabbitMQ
4. Spring AMQP
5. Docker with docker-compose (sobe uma imagem do RabbitMQ baseada na última versão)
6. Logback-access
7. MDC

## Configurações
1. Faça a clonagem do projeto. Tenha certeza que possui instalado as tecnologias citadas acima em seu computador
2. No diretório raíz do projeto, suba o RabbitMQ:
```
docker-compose up
```
3. Realizar o Build do projeto:
```
mvn clean install
```
4. Execute o projeto:
```
java -jar calculator-rest/target/calculator-rest-0.0.1-SNAPSHOT.jar
```

## Exemplos das chamadas das APIs no browser:

### Soma (sum)
```
localhost:8080/sum?a=12.5&b=22.7
```
### Subtração (subtraction)
```
localhost:8080/subtraction?a=15&b=3.5
```
### Multiplicação (multiplication)
```
localhost:8080/multiplication?a=15&b=3.3
```
### Divisão (division)
```
localhost:8080/division?a=15&b=3.2
```


