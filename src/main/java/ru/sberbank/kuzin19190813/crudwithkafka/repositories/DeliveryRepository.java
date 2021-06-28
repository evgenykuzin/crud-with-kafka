package ru.sberbank.kuzin19190813.crudwithkafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
