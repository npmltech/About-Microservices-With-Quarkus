# About-Microservices-With-Quarkus

## 📋 Introdução

Projeto de Microsserviços com Java Quarkus.
Trabalho da pós-graduação do Instituto INFNET - Matéria de Microsserviços.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Ter instalada a versão do Java JDK 17 ou superior;
* Ter instalado o Apache Maven 3.x.x;
* Utilizar uma IDE para Java - Eclipse, IntelliJ ou Visual Studio Code;
* Possuir o Docker + Docker Compose devidamente instalados em sua máquina.

## 🚀 Realizando o Setup

- O Projeto se encontra no diretório: **About-Microservices-Java-Web**

1. Clone o repositório;

2. Inicie a base de dados em container encontrado na pasta *docker-compose.yml*;

3. Execute o comando abaixo (Rodando os Testes):
```bash
mvn clean test
```
4. Execute o comando para executar a aplicação: 
```bash
mvn clean && mvn spring-boot:run ou
./mvnw compile quarkus:dev
```

5. O serviço roda na porta: 8082.

6. Documentação das APIs: http://localhost:8082/q/swagger-ui/

![swagger-ui.png](swagger-imagem%2Fswagger-ui.png)

### Comandos úteis - Quarkus

#### Gerar um executável:

- No diretório raiz, executar o comando:
```java
./mvnw clean install -Dnative -DskipTests -DQuarkus.native.container-build=true
```

- Ir para a pasta target/ e dentro dela executar o seguinte comando:
```shell
./about-microservices-java-quarkus-1.0.0-BETA-runner
```

- Gerando uma imagem docker utilizando o Dockerfile.native do Quarkus. No diretório raiz, execute:
```dockerfile
docker build -f src/main/docker/Dockerfile.native -t nikylima/getting-started .
```
