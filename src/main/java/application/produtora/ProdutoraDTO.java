package application.produtora;

public record ProdutoraDTO(long id, String nome) {
    public ProdutoraDTO(Produtora dados) {
        this(dados.getId(), dados.getNome());
    }
}
