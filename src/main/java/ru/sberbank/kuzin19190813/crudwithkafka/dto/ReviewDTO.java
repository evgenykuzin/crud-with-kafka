package ru.sberbank.kuzin19190813.crudwithkafka.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.EntityMapper;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityMapper(className = Review.class)
public class ReviewDTO extends AbstractDTO {
    String comment;
    Integer estimate;
    Long productId;
    Long clientId;
}
