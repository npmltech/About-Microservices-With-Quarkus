package br.com.npml.aluno.microsservicos.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;

import java.util.Objects;

import static java.lang.System.out;

@Entity
@Cacheable
public class Cliente extends PanacheEntity {

    private Long id;
    private String nomeCliente;
    private String nomeDocumento;
    private Long numDocumentoPrincipal;
    private Boolean bloqueio;
    private String observacao;
    private String metodoPagamento;

    public Cliente() {
    }

    public Cliente(Long id, String nomeCliente, String nomeDocumento, Long numDocumentoPrincipal, Boolean bloqueio, String observacao, String metodoPagamento) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeDocumento = nomeDocumento;
        this.numDocumentoPrincipal = numDocumentoPrincipal;
        this.bloqueio = bloqueio;
        this.observacao = observacao;
        this.metodoPagamento = metodoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public Long getNumDocumentoPrincipal() {
        return numDocumentoPrincipal;
    }

    public void setNumDocumentoPrincipal(Long numDocumentoPrincipal) {
        this.numDocumentoPrincipal = numDocumentoPrincipal;
    }

    public Boolean isBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public String toString() {
        return
            "{"
                + "{"
                + "      \"idCliente\": \"" + id + "\","
                + "      \"nomeCliente\": \"" + nomeCliente + "\","
                + "      \"nomeDocumento\" : \"" + nomeDocumento + "\","
                + "      \"numDocumentoPrincipal\" : \"" + numDocumentoPrincipal + "\","
                + "      \"bloqueio\" : \"" + bloqueio + "\","
                + "      \"observacao\" : \"" + observacao + "\","
                + "      \"metodoPagamento\" : \"" + metodoPagamento + "\""
                + "}"
            + "}";
            //
        //
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return bloqueio == cliente.bloqueio
            && Objects.equals(id, cliente.id)
            && Objects.equals(nomeCliente, cliente.nomeCliente)
            && Objects.equals(nomeDocumento, cliente.nomeDocumento)
            && Objects.equals(numDocumentoPrincipal, cliente.numDocumentoPrincipal)
            && Objects.equals(observacao, cliente.observacao)
            && Objects.equals(metodoPagamento, cliente.metodoPagamento);
        //
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            nomeCliente,
            nomeDocumento,
            numDocumentoPrincipal,
            bloqueio,
            observacao,
            metodoPagamento
        );
    }

    public static void main(String[] args) {
        out.print(
            new Cliente(
                (long) 1,
                "Mario Martins",
                "RG",
                (long) 123123123,
                false,
                "TESTE TESTE",
                "Crédito"
            )
        );
    }
}
