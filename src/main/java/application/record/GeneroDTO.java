package application.record;

import application.model.Genero;

public record GeneroDTO(long id, String nome) {
    public GeneroDTO(Genero dados) {
        this(dados.getId(), dados.getNome());
    }
}
