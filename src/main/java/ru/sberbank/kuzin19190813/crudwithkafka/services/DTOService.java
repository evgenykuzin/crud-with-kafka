package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.services.util.Search;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.SuperMapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class DTOService <D extends AbstractDTO, E extends AbstractEntity, R extends JpaRepository<E, Long>> extends JpaService<E, R> {
    @Autowired
    private SuperMapper superMapper;

    public DTOService(R jpaRepository) {
        super(jpaRepository);
    }

    public Long saveDto(D dto) {
        E e = superMapper.toEntity(dto);
        return save(e);
    }

    public D getDtoById(Long id) {
        E e = getById(id);
        return superMapper.toDto(e);
    }

    public List<D> findAllDto() {
        return findAll()
                .stream()
                .map((Function<E, D>) e -> superMapper.toDto(e))
                .collect(Collectors.toList());
    }

    public boolean deleteDto(D dto) {
        if (dto != null) {
            E entity = superMapper.toEntity(dto);
            if (entity != null) {
                delete(entity);
                return true;
            }
        }
        return false;
    }

    public List<D> findDtoBy(Search... searches) {
        return findBy(searches)
                .stream()
                .map((Function<E, D>)e -> superMapper.toDto(e))
                .collect(Collectors.toList());
    }

    public List<D> findDtoBy(String key, Object value) {
        return findDtoBy(new Search(key, value));
    }
}
