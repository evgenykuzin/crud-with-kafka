package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;

public abstract class CreatorService<D extends AbstractDTO, E extends AbstractEntity, R extends JpaRepository<E, Long>> extends DTOService<D, E, R>{
    public CreatorService(R jpaRepository) {
        super(jpaRepository);
    }

    public Long create(String name) {
        E newObj = getNew(name);
        Long id = save(newObj);
        System.out.println("newObj = " + newObj);
        return id;
    }

    protected abstract E getNew(String name);
}
