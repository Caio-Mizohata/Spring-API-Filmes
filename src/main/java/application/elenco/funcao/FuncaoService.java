package application.elenco.funcao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FuncaoService {
    @Autowired
    private FuncaoRepository funcaoRepo;

    public Iterable<FuncaoDTO> getAll() {
        return funcaoRepo.findAll().stream().map(FuncaoDTO::new).toList();
    }

    public FuncaoDTO getOne(long id) {
        Optional<Funcao> resultado = funcaoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Função não encontrada");
        }

        return new FuncaoDTO(resultado.get());
    }

    public FuncaoDTO insert(FuncaoInsertDTO dados) {
        return new FuncaoDTO(funcaoRepo.save(new Funcao(dados)));
    }

    public FuncaoDTO update(long id, FuncaoInsertDTO dadosFuncao) {
        Optional<Funcao> resultado = funcaoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Função não encontrada");
        }

        resultado.get().setDescricao(dadosFuncao.descricao());
        return new FuncaoDTO(funcaoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if (!funcaoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Função não encontrada");
        }

        funcaoRepo.deleteById(id);
    }
}
