package ru.sberbank.kuzin19190813.crudwithkafka.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.EntityMapper;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityMapper(className = Client.class)
public class ClientDTO extends AbstractDTO {
    String clientName;
    List<Long> reviewIds;
    List<Long> orderIds;
}
