package ru.sberbank.kuzin19190813.crudwithkafka.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.EntityMapper;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityMapper(className = Shop.class)
public class ShopDTO extends AbstractDTO {
    String shopName;
    Double rating;
    List<Long> productIds;
    List<Long> orderIds;
}
