package application.filmes;

import application.generos.GeneroDTO;

public record FilmeDTO(long id, String titulo, GeneroDTO genero) {
    public FilmeDTO (Filme dados) {
        this(dados.getId(), dados.getTitulo(), new GeneroDTO(dados.getGenero()));
    }
}
