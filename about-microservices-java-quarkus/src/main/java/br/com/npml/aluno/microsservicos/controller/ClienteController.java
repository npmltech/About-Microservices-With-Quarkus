package br.com.npml.aluno.microsservicos.controller;

import br.com.npml.aluno.microsservicos.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.CompositeException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

import static java.lang.String.*;

@Path("/api/v1")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ClienteController {

    private static final Logger LOGGER = Logger.getLogger(ClienteController.class.getName());

    @GET
    @Operation(summary = "Retorna todos os clientes cadastrados")
    @Path("/clientes")
    public Uni<List<Cliente>> getAll() {
        LOGGER.info("-- Listando todos clientes - GET_ALL --");
        return Cliente.listAll();
    }

    @GET
    @Operation(summary = "Retorna um cliente cadastrado utilizando Id")
    @Path("/cliente/{id}")
    public Uni<Cliente> getById(Long id) {
        LOGGER.info(format("-- Devolvendo um cliente por id: %d - GET_BY_ID --", id));
        return Cliente.findById(id);
    }

    @POST
    @Operation(summary = "Cadastra um novo cliente")
    @Path("/cliente")
    public Uni<RestResponse<Cliente>> create(Cliente cliente) {
        LOGGER.info("-- Inserindo um novo cliente - CREATE --");
        if (cliente != null && cliente.getId() == null)
            return Panache.withTransaction(cliente::persist)
                .replaceWith(RestResponse.status(RestResponse.Status.CREATED, cliente));
            //
        //
        throw new WebApplicationException("O ID foi definido de forma inválida na requisição.", 422);
    }

    @PUT
    @Operation(summary = "Altera os dados de um cliente cadastrado")
    @Path("/cliente/{id}")
    public Uni<RestResponse<Cliente>> update(Long id, Cliente cliente) {
        LOGGER.info(format("-- Atualizando um cliente por id: %d - UPDATE_BY_ID --", id));
        //
        Cliente todosDadosCliente = new Cliente(
            id,
            cliente.getNomeCliente(),
            cliente.getNomeDocumento(),
            cliente.getNumDocumentoPrincipal(),
            cliente.isBloqueio(),
            cliente.getObservacao(),
            cliente.getMetodoPagamento()
        );
        //
        if (ClienteController.isDadosClientePreenchido(cliente))
            return Panache.withTransaction(() -> Cliente.<Cliente>findById(id)
                .onItem().ifNotNull().invoke(cli -> cli.setNomeCliente(cliente.getNomeCliente()))
                .onItem().ifNotNull().invoke(cli -> cli.setNomeDocumento(cliente.getNomeDocumento()))
                .onItem().ifNotNull().invoke(cli -> cli.setNumDocumentoPrincipal(cliente.getNumDocumentoPrincipal()))
                .onItem().ifNotNull().invoke(cli -> cli.setBloqueio(cliente.isBloqueio()))
                .onItem().ifNotNull().invoke(cli -> cli.setObservacao(cliente.getObservacao()))
                .onItem().ifNotNull().invoke(cli -> cli.setMetodoPagamento(cliente.getMetodoPagamento()))
            )
            .onItem().ifNotNull().transform(result -> RestResponse.status(Response.Status.OK, cliente))
            .onItem().ifNull().continueWith(() -> RestResponse.status(Response.Status.NOT_FOUND, todosDadosCliente))
            .replaceWith(RestResponse.status(RestResponse.Status.CREATED, todosDadosCliente));
        //
        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Existem um ou mais dados inválidos enviados na requisição: \r\n" + todosDadosCliente)
            .build()
        );
    }

    @DELETE
    @Operation(summary = "Remove um cliente cadastrado utilizando Id")
    @Path("/cliente/{id}")
    public Uni<RestResponse<Cliente>> delete(Long id) {
        LOGGER.info(format("-- Removendo um cliente por id: %d - DELETE_BY_ID --", id));
        return Panache.withTransaction(() -> Cliente.deleteById(id))
            .map(deleted -> deleted
                ? RestResponse.status(RestResponse.Status.NO_CONTENT)
                : RestResponse.status(RestResponse.Status.NOT_FOUND)
            );
        //
    }

    private static boolean isDadosClientePreenchido(Cliente cliente) {
        return cliente != null
            && cliente.getNomeCliente() != null
            && cliente.getNomeDocumento() != null
            && cliente.getNumDocumentoPrincipal() != null
            && cliente.isBloqueio() != null
            && cliente.getObservacao() != null
            && cliente.getMetodoPagamento() != null;
        //
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Falha ao processar a requisição.", exception);

            Throwable throwable = exception;

            int code = throwable instanceof WebApplicationException ?
                ((WebApplicationException) exception).getResponse().getStatus() : 500;
            //

            /* Esta é uma exceção Mutiny e acontece,
             * por exemplo, quando tentamos inserir um novo
             * cliente mas o nome já está no banco de dados
             */
            if (throwable instanceof CompositeException) throwable = throwable.getCause();

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson
                .put("exceptionType", throwable.getClass().getName())
                .put("code", code);
            //

            if (exception.getMessage() != null) exceptionJson.put("error", throwable.getMessage());

            return Response.status(code)
                .entity(exceptionJson)
                .build();
            //
        }
    }
}
