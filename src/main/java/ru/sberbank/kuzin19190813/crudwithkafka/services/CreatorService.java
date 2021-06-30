package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.AbstractBaseRepository;

public abstract class CreatorService<D extends AbstractDTO, E extends AbstractEntity, R extends AbstractBaseRepository<E>> extends DTOService<D, E, R>{
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
