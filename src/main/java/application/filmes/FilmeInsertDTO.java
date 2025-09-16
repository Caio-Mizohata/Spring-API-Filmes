package application.filmes;

import java.util.List;

public record FilmeInsertDTO(long idGenero, String titulo, List<Long> IdsProdutoras) {
    
}
