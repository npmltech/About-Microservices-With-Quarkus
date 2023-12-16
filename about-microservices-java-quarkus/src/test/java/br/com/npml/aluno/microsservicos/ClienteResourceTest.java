package br.com.npml.aluno.microsservicos;

import br.com.npml.aluno.microsservicos.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class ClienteResourceTest {

    private static Response response;
    private final String URI = "/api/v1/";
    private Map<String, Object> clienteRequest;

    @Order(1)
    @DisplayName(ClienteCasosDeTeste.GET_ALL)
    @Test
    void testGetTodosClientes() {
        response = given()
            .accept(ContentType.JSON)
            .when()
            .get("%sclientes".formatted(URI));

        response.prettyPrint();

        response
            .then()
            .assertThat()
            .body(
                    "size()", greaterThan(0),
                    "size()", is(4)
            )
            .statusCode(SC_OK);
        //
    }

    @Order(2)
    @DisplayName(ClienteCasosDeTeste.GET_BY_ID)
    @Test
    void testGetClientePorId() {
        Cliente clientes = given()
            .accept(ContentType.JSON)
            .when()
            .get("%scliente/1".formatted(URI))
            .then()
            .statusCode(SC_OK)
            .extract()
            .as(Cliente.class);
        //

        assertThat(clientes.toString().length(), greaterThan(0));
        assertThat(
            clientes.toString(),
            is(
                "{{" +
                    "      \"idCliente\": \"1\"," +
                    "      \"nomeCliente\": \"Mario Martins\"," +
                    "      \"nomeDocumento\" : \"RG\"," +
                    "      \"numDocumentoPrincipal\" : \"123456\"," +
                    "      \"bloqueio\" : \"false\"," +
                    "      \"observacao\" : \"TESTE TESTE\"," +
                    "      \"metodoPagamento\" : \"Crédito\"" +
                "}}"
            )
        );
    }

    @Order(3)
    @DisplayName(ClienteCasosDeTeste.INSERT)
    @Test
    void testInserirCliente() {
        this.clienteRequest = Map.of(
            "nomeCliente", "CLIENTE TESTE ANDRÉ RIBEIRO NETO",
            "nomeDocumento", "RG",
            "numDocumentoPrincipal", "123123123",
            "bloqueio", "false",
            "observacao", "TESTE TESTE TESTE TESTE TESTE TESTE",
            "metodoPagamento", "PIX"
        );

        response = given()
            .contentType("application/json")
            .body(clienteRequest)
            .when()
            .post("%scliente".formatted(URI));

        response.prettyPrint();

        response
            .then()
            .assertThat()
            .body(
                    "size()", greaterThan(0)
            )
            .statusCode(SC_CREATED);
        //
    }

    @Order(4)
    @DisplayName(ClienteCasosDeTeste.UPDATE_BY_ID)
    @Test
    void testAtualizarCliente() {
        this.clienteRequest = Map.of(
            "nomeCliente", "Amanda TESTE NOVO UPDATE",
            "nomeDocumento", "RG",
            "numDocumentoPrincipal", 123456,
            "bloqueio", true,
            "observacao", "TESTE TESTE",
            "metodoPagamento", "Cartão Flex"
        );

        response = given()
            .contentType("application/json")
            .body(clienteRequest)
            .when()
            .put("%scliente/4".formatted(URI));

        response.prettyPrint();

        response
            .then()
            .assertThat()
            .body(
                    "size()", greaterThan(0)
            )
            .statusCode(SC_CREATED);
        //
    }

    @Order(5)
    @DisplayName(ClienteCasosDeTeste.DELETE_BY_ID)
    @Test
    void testRemoverCliente() {
        response = given()
            .contentType("application/json")
            .when()
            .delete("%scliente/4".formatted(URI));

        response.prettyPrint();

        response
            .then()
            .assertThat()
            .statusCode(SC_NO_CONTENT);
        //
    }
}
