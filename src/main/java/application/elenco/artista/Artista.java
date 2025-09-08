package application.elenco.artista;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "artistas")
@Getter
@Setter
@NoArgsConstructor
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    public Artista(ArtistaDTO dados) {
        this.setId(dados.id());
        this.setNome(dados.nome());
    }

    public Artista(ArtistaInsertDTO dados) {
        this.setNome(dados.nome());
    }
}
