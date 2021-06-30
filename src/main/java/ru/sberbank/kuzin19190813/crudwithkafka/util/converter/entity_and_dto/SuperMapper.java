package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;

@Component
public class SuperMapper {
    @Autowired
    ModelMapper modelMapper;

    public final <E extends AbstractEntity, D extends AbstractDTO> D toDto(E e) {
        if (e == null) return null;
        DtoMapper annotation = e.getClass().getAnnotation(DtoMapper.class);
        Class<E> entityClass = (Class<E>) e.getClass();
        Class<D> dtoClass = (Class<D>) annotation.className();
        DefaultMapper<E, D> mapper = getMapper(entityClass, dtoClass);
        return mapper.toDto(e);
    }

    public final <E extends AbstractEntity, D extends AbstractDTO> E toEntity(D d) {
        if (d == null) return null;
        EntityMapper annotation = d.getClass().getAnnotation(EntityMapper.class);
        Class<E> entityClass = (Class<E>) annotation.className();
        Class<D> dtoClass = (Class<D>) d.getClass();
        DefaultMapper<E, D> mapper = getMapper(entityClass, dtoClass);
        return mapper.toEntity(d);
    }

    private <E extends AbstractEntity, D extends AbstractDTO> DefaultMapper<E, D> getMapper(Class<E> entityClass, Class<D> dtoClass) {
        return new DefaultMapper<>(modelMapper, entityClass, dtoClass);
    }
}
