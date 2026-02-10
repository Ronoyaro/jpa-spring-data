package study.ronoyaro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.ronoyaro.dao.AutorDao;
import study.ronoyaro.domain.Autor;
import study.ronoyaro.domain.InfoAutor;

import java.util.List;

@RestController
@RequestMapping("v1/autores")
@RequiredArgsConstructor
@Slf4j
public class AutorController {
    private final AutorDao autorDao;

    @PostMapping
    public Autor save(@RequestBody Autor autor) {
        log.debug("request to save '{}'", autor);
        return autorDao.save(autor);
    }

    @PutMapping
    public void update(@RequestBody Autor autor) {
        log.debug("request to update '{}'", autor);
        autorDao.update(autor);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        log.debug("request to delete '{}'", id);
        autorDao.delete(id);
    }

    @GetMapping("{id}")
    public Autor findById(@PathVariable Long id) {
        log.debug("request to find '{}'", id);
        return autorDao.findById(id);
    }

    @GetMapping
    public List<Autor> findAll() {
        log.info("request to find all Autores");
        return autorDao.findAll();
    }

    @GetMapping("nameOrLastName")
    public List<Autor> findByNameOrLastName(@RequestParam String termo) {
        log.debug("request to find Autor by '{}'", termo);
        return autorDao.findByNameOrLastName(termo);
    }

    @GetMapping("count")
    public Long countAllAutores() {
        log.info("request to count all actores");
        return autorDao.countAllAutores();
    }

// v1/autores/1/infoAutor

    @PutMapping("{id}/info")
    public Autor saveInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return autorDao.saveInfoAutor(id, infoAutor);
    }

    @GetMapping("info")
    public List<Autor> findByCargo(@RequestParam String cargo) {
        return autorDao.findByCargo(cargo);
    }
}
