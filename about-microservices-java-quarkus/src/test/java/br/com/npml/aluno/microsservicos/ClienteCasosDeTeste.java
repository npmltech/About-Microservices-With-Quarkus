package br.com.npml.aluno.microsservicos;

public class ClienteCasosDeTeste {

    public static final String GET_ALL =
        "Testa se retorna uma lista maior que zero, igual a 4 e status code é 200";
    public static final String GET_BY_ID =
        "Testa se retorna um cliente por Id, se os dados desse cliente estão corretos e status code é 200";
    public static final String INSERT =
        "Testa se o cliente foi inserido no cadastro e status code é 201";
    public static final String UPDATE_BY_ID =
        "Testa se um cliente pode ser alterado, passando para a chamada um Id e status code é 201";
    public static final String DELETE_BY_ID =
        "Testa se um cliente pode ser removido, passando para a chamada um Id e status code é 204";
}
