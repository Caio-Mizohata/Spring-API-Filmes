package application.elenco.funcao;

public record FuncaoDTO(long id, String descricao) {
    public FuncaoDTO(Funcao dados) {
        this(dados.getId(), dados.getDescricao());
    }
}