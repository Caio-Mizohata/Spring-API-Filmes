package application.elenco.artista;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepo;

    public Iterable<ArtistaDTO> getAll() {
        return artistaRepo.findAll().stream().map(ArtistaDTO::new).toList();
    }

    public ArtistaDTO getOne(long id) {
        Optional<Artista> resultado = artistaRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }

        return new ArtistaDTO(resultado.get());
    }

    public ArtistaDTO insert(ArtistaInsertDTO dados) {
        return new ArtistaDTO(artistaRepo.save(new Artista(dados)));
    }

    public ArtistaDTO update(long id, ArtistaInsertDTO dadosArtista) {
        Optional<Artista> resultado = artistaRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }

        resultado.get().setNome(dadosArtista.nome());
        return new ArtistaDTO(artistaRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if (!artistaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }

        artistaRepo.deleteById(id);
    }
}
