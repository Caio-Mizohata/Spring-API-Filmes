package application.elenco.artista;

public record ArtistaDTO(long id, String nome) {
    public ArtistaDTO(Artista dados) {
        this(dados.getId(), dados.getNome());
    }
}
