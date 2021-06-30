package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.services.util.Search;

import java.util.List;

public abstract class JpaService<E extends AbstractEntity, R extends JpaRepository<E, Long>> {
    protected R repository;

    public JpaService(R repository) {
        this.repository = repository;
    }

    public Long save(E e) {
        repository.save(e);
        return e.getId();
    }

    public E getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public void delete(E e) {
        repository.delete(e);
    }

    public List<E> findBy(Search... searches) {
        return null; //TODO
    }

    public List<E> findBy(String key, Object value) {
        return findBy(new Search(key, value));
    }
}
