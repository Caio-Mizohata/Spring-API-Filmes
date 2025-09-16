package application.produtoras;

public record ProdutoraInsertDTO(String nome) {
    public ProdutoraInsertDTO(Produtora dados) {
        this(dados.getNome());
    }
}
