package application.filmes;

import application.generos.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "filmes")
@Getter
@Setter
@NoArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;

    public Filme(FilmeDTO dados) {
        this.setId(dados.id());
        this.setTitulo(dados.titulo());
        this.setGenero(new Genero(dados.genero()));
    }

    public Filme(FilmeInsertDTO dados) {
        this.setTitulo(dados.titulo());
        Genero genero = new Genero();
        genero.setId(dados.idGenero());
        this.setGenero(genero);
    }
}
