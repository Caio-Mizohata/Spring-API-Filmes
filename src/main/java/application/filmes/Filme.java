package application.filmes;

import java.util.Set;
import java.util.stream.Collectors;

import application.generos.Genero;
import application.produtoras.Produtora;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany
    @JoinTable(name = "filmes_possuem_produtoras", joinColumns = @JoinColumn(name = "id_filme"), inverseJoinColumns = @JoinColumn(name = "id_produtora"))
    private Set<Produtora> produtoras;

    public Filme(FilmeDTO dados) {
        this.setId(dados.id());
        this.setTitulo(dados.titulo());
        this.setGenero(new Genero(dados.genero()));
        this.setProdutoras(dados.produtoras().stream().map(Produtora::new).collect(Collectors.toSet()));
    }
    
    public Filme(FilmeInsertDTO dados) {
        this.setTitulo(dados.titulo());
        Genero genero = new Genero();
        genero.setId(dados.idGenero());
        this.setGenero(genero);

        Set<Produtora> produtoras = dados.IdsProdutoras().stream().map(p -> new Produtora(p)).collect(Collectors.toSet());
        this.setProdutoras(produtoras);
    }
}
