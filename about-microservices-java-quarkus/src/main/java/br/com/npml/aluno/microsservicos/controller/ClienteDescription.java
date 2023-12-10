package br.com.npml.aluno.microsservicos.controller;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "infnet", description = "Microsserviços - Base de Estudo."),
                @Tag(name = "cliente", description = "API de Cliente para o estudo sobre microsserviços com Quarkus.")
        },
        info = @Info(
                title = "API Cliente - Coleção com dados de cadastro de clientes",
                version = "1.0.0-BETA",
                contact = @Contact(
                name = "Niky Lima",
                url = "https://github.com/npmltech/About-Microservices-With-Quarkus",
                email = "devqasp@gmail.com"
        ),
        license = @License(
                name = "Apache 2.0",
                url = "https:www.apache.org/licenses/LICENSE-2.0.html")
        )
        /* servers = {
                @Server(url = "http:localhost:8082")
        } */
)
public class ClienteDescription extends Application {
}
