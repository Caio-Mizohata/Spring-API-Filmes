package application.produtoras;

public record ProdutoraDTO(long id, String nome) {
    public ProdutoraDTO(Produtora dados) {
        this(dados.getId(), dados.getNome());
    }
}
