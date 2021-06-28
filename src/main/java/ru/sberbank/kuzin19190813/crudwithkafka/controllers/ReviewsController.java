package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ReviewServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewsController extends CrudController<Review, ReviewDTO> {
    @Autowired
    public ReviewsController(ReviewServiceImpl reviewService, KafkaProducerServiceImpl kafkaProducerService) {
        super(reviewService, kafkaProducerService);
    }
}
