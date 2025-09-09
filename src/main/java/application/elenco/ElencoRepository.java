package application.elenco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ElencoRepository extends JpaRepository<Elenco, Long> {
    
}
