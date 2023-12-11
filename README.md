# About-Microservices-With-Quarkus
A microservice example using Quarkus.

### Comandos úteis - Quarkus

#### Gerar um executável

- No diretório raiz, executar o comando:
```java
./mvnw clean install -Dnative -DskipTests -DQuarkus.native.container-build=true
```

- Ir para a pasta target/ e dentro dela executar o seguinte comando:
```shell
./about-microservices-java-quarkus-1.0.0-BETA-runner
```
