package study.ronoyaro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.ronoyaro.dao.AutorDao;
import study.ronoyaro.domain.Autor;

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
}
