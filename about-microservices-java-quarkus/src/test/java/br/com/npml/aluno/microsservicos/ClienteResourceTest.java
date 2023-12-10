package br.com.npml.aluno.microsservicos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ClienteResourceTest {

    @Test
    void testClientesEndpoint() {
        given()
            .when().get("/api/v1/clientes")
            .then()
            .statusCode(200);
        //
    }
}