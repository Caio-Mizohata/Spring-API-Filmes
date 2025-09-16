
package application.produtora;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoraService {
    @Autowired
    private ProdutoraRepository produtoraRepo;

    public Iterable<ProdutoraDTO> getAll() {
        return produtoraRepo.findAll().stream().map(ProdutoraDTO::new).toList();
    }

    public ProdutoraDTO insert(ProdutoraInsertDTO novaProdutora) {

        Produtora produtora = new Produtora();
        produtora.setNome(novaProdutora.nome());

        return new ProdutoraDTO(produtoraRepo.save(produtora));
    }

    public ProdutoraDTO getOne(Long id) {
        Optional<Produtora> resultado = produtoraRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtora nao encontrada");
        }

        return new ProdutoraDTO(resultado.get());
    }

    public ProdutoraDTO update(long id, ProdutoraInsertDTO novosDados) {
        Optional<Produtora> resultado = produtoraRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtora nao encontrada");
        }

        resultado.get().setNome(novosDados.nome());

        return new ProdutoraDTO(produtoraRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!produtoraRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtora nao encontrada");
        }
        
        produtoraRepo.deleteById(id);
    }
}
