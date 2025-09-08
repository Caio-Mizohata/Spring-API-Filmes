package application.elenco.funcao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcoes")
@Getter
@Setter
@NoArgsConstructor
public class Funcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String descricao;

    public Funcao(FuncaoDTO dados) {
        this.setId(dados.id());
        this.setDescricao(dados.descricao());
    }

    public Funcao(FuncaoInsertDTO dados) {
        this.setDescricao(dados.descricao());
    }
}
