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

    public <E extends AbstractEntity, D extends AbstractDTO> D toDto(E e) {
        if (e == null) return null;
        DtoMapper annotation = e.getClass().getAnnotation(DtoMapper.class);
        Class<D> dtoClass = (Class<D>) annotation.className();
        Class<E> entityClass = (Class<E>) e.getClass();
        AbstractMapper<E, D> mapper = getMapper(entityClass, dtoClass);
        return mapper.toDto(e);
    }

    public <E extends AbstractEntity, D extends AbstractDTO> E toEntity(D d) {
        if (d == null) return null;
        EntityMapper annotation = d.getClass().getAnnotation(EntityMapper.class);
        Class<D> dtoClass = (Class<D>) d.getClass();
        Class<E> entityClass = (Class<E>) annotation.className();
        AbstractMapper<E, D> mapper = getMapper(entityClass, dtoClass);
        return mapper.toEntity(d);
    }

    private <E extends AbstractEntity, D extends AbstractDTO> AbstractMapper<E, D> getMapper(Class<E> entityClass, Class<D> dtoClass) {
        return new AbstractMapper<>(modelMapper, entityClass, dtoClass) {
        };
    }
}
