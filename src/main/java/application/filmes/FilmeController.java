package application.filmes;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    FilmeService filmeService;

    @GetMapping
    public Iterable<FilmeDTO> getAll() {
        return this.filmeService.getAll();
    }

    @GetMapping("/{id}")
    public FilmeDTO getOne(@PathVariable long id) {
        return this.filmeService.getOne(id);
    }

    @PostMapping
    public FilmeDTO insert(@RequestBody FilmeInsertDTO dadosNovos) {
        return this.filmeService.insert(dadosNovos);
    }

    @PutMapping("/{id}")
    public FilmeDTO update(@PathVariable long id, @RequestBody FilmeInsertDTO dados) {
        return this.filmeService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.filmeService.delete(id);
    }
}
