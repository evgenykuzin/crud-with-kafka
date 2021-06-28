package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ReviewRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.ReviewService;

@Service
public class ReviewServiceImpl extends DTOService<ReviewDTO, Review, ReviewRepository> implements ReviewService {
    @Autowired
    public ReviewServiceImpl(ReviewRepository ReviewRepository) {
        super(ReviewRepository);
    }

    @Override
    public Review createReview() {
        return null;
    }
}
