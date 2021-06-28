package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ReviewRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.ReviewMapper;

public interface ReviewService {
    Review createReview();
}
