package application.produtora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtoras")
public class ProdutoraController {
    @Autowired
    private ProdutoraService produtoraService;

    @GetMapping
    public Iterable<ProdutoraDTO> getAll() {
        return this.produtoraService.getAll();
    }

    @PostMapping
    public ProdutoraDTO insert(@RequestBody ProdutoraInsertDTO novosDados) {
        return this.produtoraService.insert(novosDados);
    }

    @GetMapping("/{id}")
    public ProdutoraDTO getOne(@PathVariable Long id) {
        return this.produtoraService.getOne(id);
    }

    @PutMapping("/{id}")
    public ProdutoraDTO update(@PathVariable Long id, @RequestBody ProdutoraInsertDTO novosDados) {
        return this.produtoraService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoraService.delete(id);
    }
}
