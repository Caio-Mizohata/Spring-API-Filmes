package application.filmes;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import application.generos.GeneroService;
import application.generos.Genero;
import application.produtoras.Produtora;
import application.produtoras.ProdutoraService;


@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepo;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ProdutoraService produtoraService;

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
        Set<Produtora> produtoras = novoFilme.IdsProdutoras().stream().map(p -> new Produtora(produtoraService.getOne(p))).collect(Collectors.toSet());

        Filme filme = new Filme();
        filme.setTitulo(novoFilme.titulo());
        filme.setGenero(genero);
        filme.setProdutoras(produtoras);

        return new FilmeDTO(filmeRepo.save(filme));
    }

    public FilmeDTO update(Long id, FilmeInsertDTO novosDados) {
        Optional<Filme> resultado = filmeRepo.findById(id);
        Set<Produtora> produtoras = novosDados.IdsProdutoras().stream().map(p -> new Produtora(produtoraService.getOne(p))).collect(Collectors.toSet());

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }

        Genero genero = new Genero(generoService.getOne(novosDados.idGenero()));

        resultado.get().setTitulo(novosDados.titulo());
        resultado.get().setGenero(genero);
        resultado.get().setProdutoras(produtoras);

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
