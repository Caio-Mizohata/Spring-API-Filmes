package application.elenco.artista;

public record ArtistaInsertDTO(String nome) {
    public ArtistaInsertDTO(Artista dados) {
        this(dados.getNome());
    }
}
