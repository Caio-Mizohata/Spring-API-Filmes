package application.elenco;

public record ElencoInsertDTO(long idFilme, long idArtista, long idFuncao) {
    public ElencoInsertDTO(Elenco dados) {
        this(dados.getFilme().getId(), dados.getArtista().getId(), dados.getFuncao().getId());
    }
}
