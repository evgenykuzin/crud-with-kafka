package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.services.util.Search;

import java.util.List;

public abstract class JpaService<E extends AbstractEntity, R extends JpaRepository<E, Long>> {
    protected R jpaRepository;

    public JpaService(R jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Long save(E e) {
        jpaRepository.save(e);
        return e.getId();
    }

    public E getById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    public List<E> findAll() {
        return jpaRepository.findAll();
    }

    public void delete(E e) {
        jpaRepository.delete(e);
    }

    public List<E> findBy(Search... searches) {
        return null; //TODO
    }

    public List<E> findBy(String key, Object value) {
        return findBy(new Search(key, value));
    }
}
