package application.generos;

public record GeneroDTO(long id, String nome) {
    public GeneroDTO(Genero dados) {
        this(dados.getId(), dados.getNome());
    }
}
