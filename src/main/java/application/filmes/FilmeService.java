package application.filmes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import application.generos.GeneroService;
import application.generos.Genero;


@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepo;
    @Autowired
    private GeneroService generoService;

    public Iterable<FilmeDTO> getAll() {
        return filmeRepo.findAll().stream().map(FilmeDTO::new).toList();
    }

    public FilmeDTO getOne(Long id) {
        Optional<Filme> resultado = filmeRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }
        
        return new FilmeDTO(resultado.get());
    }

    public FilmeDTO insert(FilmeInsertDTO novoFilme) {
        Genero genero = new Genero(generoService.getOne(novoFilme.idGenero()));

        Filme filme = new Filme();
        filme.setTitulo(novoFilme.titulo());
        filme.setGenero(genero);

        return new FilmeDTO(filmeRepo.save(filme));
    }

    public FilmeDTO update(Long id, FilmeInsertDTO novosDados) {
        Optional<Filme> resultado = filmeRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }

        Genero genero = new Genero(generoService.getOne(novosDados.idGenero()));

        resultado.get().setTitulo(novosDados.titulo());
        resultado.get().setGenero(genero);

        return new FilmeDTO(filmeRepo.save(resultado.get()));
    }

    public void delete(Long id) {
        Optional<Filme> resultado = filmeRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }

        filmeRepo.deleteById(id);
    }
}
