package application.generos;

public record GeneroInsertDTO(String nome) {
    public GeneroInsertDTO(Genero dados) {
        this(dados.getNome());
    }
}
