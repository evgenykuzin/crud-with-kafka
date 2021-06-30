package ru.sberbank.kuzin19190813.crudwithkafka.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.City;

@Repository
public interface CityRepository extends AbstractBaseRepository<City> {
    @Override
    @Modifying
    @Query(
            value = "SET REFERENTIAL_INTEGRITY FALSE; " +
                    "truncate table CITY; " +
                    "SET REFERENTIAL_INTEGRITY TRUE;",
            nativeQuery = true
    )
    void truncateTable();
}
