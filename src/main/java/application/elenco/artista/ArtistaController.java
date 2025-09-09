package application.elenco.artista;

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
@RequestMapping("/artistas")
public class ArtistaController {
    @Autowired
    ArtistaService artistaService;

    @GetMapping
    public Iterable<ArtistaDTO> getAll() {
        return this.artistaService.getAll();
    }

    @GetMapping("/{id}")
    public ArtistaDTO getOne(@PathVariable long id) {
        return this.artistaService.getOne(id);
    }

    @PostMapping
    public ArtistaDTO insert(@RequestBody ArtistaInsertDTO dadosNovos) {
        return this.artistaService.insert(dadosNovos);
    }

    @PutMapping("/{id}")
    public ArtistaDTO update(@PathVariable long id, @RequestBody ArtistaInsertDTO dados) {
        return this.artistaService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.artistaService.delete(id);
    }
}
