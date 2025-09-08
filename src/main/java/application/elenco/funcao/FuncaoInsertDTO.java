package application.elenco.funcao;

public record FuncaoInsertDTO(String descricao) {
    public FuncaoInsertDTO(Funcao dados) {
        this(dados.getDescricao());
    }
}
