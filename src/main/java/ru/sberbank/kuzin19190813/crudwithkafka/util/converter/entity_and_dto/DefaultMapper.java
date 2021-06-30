package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import org.modelmapper.ModelMapper;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;

import java.util.Objects;

public class DefaultMapper<E extends AbstractEntity, D extends AbstractDTO> implements Mapper<E, D> {
    private final ModelMapper mapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    DefaultMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.mapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }
}
