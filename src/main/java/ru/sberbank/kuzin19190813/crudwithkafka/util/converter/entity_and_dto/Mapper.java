package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.DTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends DTO> {
    D toDto(E entity);

    E toEntity(D dto);
}
