package ru.sberbank.kuzin19190813.crudwithkafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
