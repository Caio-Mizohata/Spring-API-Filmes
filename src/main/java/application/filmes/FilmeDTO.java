package application.filmes;

import java.util.List;

import application.generos.GeneroDTO;
import application.produtoras.ProdutoraDTO;

public record FilmeDTO(long id, String titulo, GeneroDTO genero, List<ProdutoraDTO> produtoras) {
    public FilmeDTO (Filme dados) {
        this(dados.getId(), dados.getTitulo(), new GeneroDTO(dados.getGenero()), dados.getProdutoras().stream().map(ProdutoraDTO::new).toList());
    }
}
