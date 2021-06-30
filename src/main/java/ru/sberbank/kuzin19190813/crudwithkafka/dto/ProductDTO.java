package ru.sberbank.kuzin19190813.crudwithkafka.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.EntityMapper;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityMapper(className = Product.class)
public class ProductDTO extends AbstractDTO {
    String productName;
    Double price;
    String barcode;
    String article;
    Long shopId;
}
