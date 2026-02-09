package study.ronoyaro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.ronoyaro.domain.Autor;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public Autor save(Autor autor) {
        manager.persist(autor);
        return autor;

    }
}
