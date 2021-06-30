package ru.sberbank.kuzin19190813.crudwithkafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;

@NoRepositoryBean
public interface AbstractBaseRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
    void truncateTable();
}
