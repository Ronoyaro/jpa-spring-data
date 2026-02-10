package study.ronoyaro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.ronoyaro.domain.Autor;
import study.ronoyaro.domain.InfoAutor;

import java.util.List;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public Autor save(Autor autor) {
        manager.persist(autor);
        return autor;
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        manager.merge(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Autor autorReference = manager.getReference(Autor.class, id);
        manager.remove(autorReference);
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return manager.find(Autor.class, id);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        String query = "SELECT a FROM Autor a";
        return manager.createQuery(query, Autor.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findByNameOrLastName(String termo) {
        String query = "SELECT a FROM Autor a where a.name like :termo or a.lastName like :termo";
        return manager.createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findByNameOrLastNamePosition(String termo) {
        String query = "SELECT a FROM Autor a where a.name like ?1 or a.lastName like ?1";
        return manager.createQuery(query, Autor.class)
                .setParameter(1, "%" + termo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Long countAllAutores() {
        String query = "SELECT count(1) from Autor a";
        return manager.createQuery(query, Long.class)
                .getSingleResult();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(Long autorId, InfoAutor infoAutor) {
        Autor autor = this.findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo) {
        String query = "SELECT a FROM Autor a where a.infoAutor.cargo like :cargo order by a.name asc";
        return manager.createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
    }
}

