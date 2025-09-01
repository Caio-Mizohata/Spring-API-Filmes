package application.model;

import application.record.GeneroDTO;
import application.record.GeneroInsertDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genero {
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Genero(GeneroDTO dados) {
        this.setId(id);
        this.setNome(dados.nome());
    }

    public Genero(GeneroInsertDTO dados) {
        this.setNome(dados.nome());
    }
}
