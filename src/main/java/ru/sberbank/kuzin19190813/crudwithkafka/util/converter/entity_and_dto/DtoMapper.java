package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DtoMapper {
    Class<? extends AbstractDTO> className();
}
