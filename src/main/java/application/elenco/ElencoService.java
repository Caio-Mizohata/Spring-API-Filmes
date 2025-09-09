package application.elenco;

import application.elenco.artista.ArtistaRepository;
import application.elenco.funcao.FuncaoRepository;
import application.filmes.FilmeRepository;
import application.filmes.Filme;
import application.elenco.artista.Artista;
import application.elenco.funcao.Funcao;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;


@Service
public class ElencoService {
    @Autowired
    private ElencoRepository elencoRepo;
    @Autowired
    private FilmeRepository filmeRepo;
    @Autowired
    private ArtistaRepository artistaRepo;
    @Autowired
    private FuncaoRepository funcaoRepo;

    public Iterable<ElencoDTO> getAll() {
        return elencoRepo.findAll().stream().map(ElencoDTO::new).toList();
    }

    public ElencoDTO getOne(long id) {
        Optional<Elenco> resultado = elencoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Elenco não encontrado");
        }
        
        return new ElencoDTO(resultado.get());
    }

    public ElencoDTO insert(ElencoInsertDTO dados) {
        Optional<Filme> resultadoFilme = filmeRepo.findById(dados.idFilme());
        Optional<Artista> resultadoArtista = artistaRepo.findById(dados.idArtista());
        Optional<Funcao> resultadoFuncao = funcaoRepo.findById(dados.idFuncao());

        String mensagem = "Dados não encontrados: ";
        boolean isError = false;

        if (resultadoFilme.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Filme";
            isError = true;
        }

        if (resultadoArtista.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Artista";
            isError = true;
        }

        if (resultadoFuncao.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Função";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagem);
        }

        Elenco novo = new Elenco();
        novo.setFilme(resultadoFilme.get());
        novo.setArtista(resultadoArtista.get());
        novo.setFuncao(resultadoFuncao.get());
        
        return new ElencoDTO(elencoRepo.save(novo));
    }

    public ElencoDTO update(long id, ElencoInsertDTO dados) {
        Optional<Elenco> resultadoElenco = elencoRepo.findById(dados.idFilme());
        Optional<Filme> resultadoFilme = filmeRepo.findById(dados.idFilme());
        Optional<Artista> resultadoArtista = artistaRepo.findById(dados.idArtista());
        Optional<Funcao> resultadoFuncao = funcaoRepo.findById(dados.idFuncao());

        if (resultadoElenco.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Elenco não encontrado");
        }


        String mensagem = "Dados não encontrados: ";
        boolean isError = false;

        if (resultadoFilme.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Filme";
            isError = true;
        }

        if (resultadoArtista.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Artista";
            isError = true;
        }

        if (resultadoFuncao.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "Função";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagem);
        }

        resultadoElenco.get().setFilme(resultadoFilme.get());
        resultadoElenco.get().setArtista(resultadoArtista.get());
        resultadoElenco.get().setFuncao(resultadoFuncao.get());

        return new ElencoDTO(elencoRepo.save(resultadoElenco.get()));
    }

    public void delete(long id) {
        if(!elencoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Elenco não encontrado");
        }

        elencoRepo.deleteById(id);
    }
}
