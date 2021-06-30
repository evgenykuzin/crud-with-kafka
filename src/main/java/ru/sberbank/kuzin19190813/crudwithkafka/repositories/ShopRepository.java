package ru.sberbank.kuzin19190813.crudwithkafka.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;

@Repository
public interface ShopRepository extends AbstractBaseRepository<Shop> {
    @Override
    @Modifying
    @Query(
            value = "SET REFERENTIAL_INTEGRITY FALSE; " +
                    "truncate table SHOP; " +
                    "SET REFERENTIAL_INTEGRITY TRUE;",
            nativeQuery = true
    )
    void truncateTable();
}
