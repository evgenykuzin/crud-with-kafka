package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.DeletedBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.ListResultBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.OkBody;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.NotFoundBody;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
public abstract class CrudController<E extends AbstractEntity, D extends AbstractDTO> {
    protected DTOService<D, E, ? extends JpaRepository<E, Long>> dtoService;
    protected KafkaProducerServiceImpl kafkaProducerService;

    public CrudController(DTOService<D, E, ? extends JpaRepository<E, Long>> dtoService, KafkaProducerServiceImpl kafkaProducerService) {
        this.dtoService = dtoService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/get/{id}")
    public Object get(@RequestHeader HashMap<String, String> httpHeaders, @PathVariable Long id) {
        String entityName = parseEntityName();
        log.info("[{}]: /get/{}", entityName, id);
        kafkaProducerService.submit(new KafkaBody("get", entityName, id));
        log.info("kafka message was sent");
        D dto = dtoService.getDtoById(id);
        return Objects.requireNonNullElseGet(dto, () -> new NotFoundBody(entityName, id));
    }

    @PostMapping("/save")
    public Object save(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody D dto) {
        String entityName = parseEntityName();
        log.info("[{}]: /save {}", entityName, dto);
        kafkaProducerService.submit(new KafkaBody("save", entityName, dto));
        log.info("kafka message was sent");
        Long id = dtoService.saveDto(dto);
        log.info("saved entity id: {}", id);
        return new OkBody();
    }

    @PutMapping("/update")
    public Object update(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody D dto) {
        String entityName = parseEntityName();
        log.info("[{}]: /update {}", entityName, dto);
        kafkaProducerService.submit(new KafkaBody("update", entityName, dto));
        log.info("kafka message was sent");
        if (dto.getId() == null) return new NotFoundBody(entityName, null);
        dtoService.saveDto(dto);
        log.info("{} updated with body: {}", entityName, dto);
        return new OkBody();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@RequestHeader HashMap<String, String> httpHeaders, @PathVariable Long id) {
        String entityName = parseEntityName();
        log.info("[{}]: /delete/{}", entityName, id);
        kafkaProducerService.submit(new KafkaBody("delete", entityName, id));
        log.info("kafka message was sent");
        boolean isDeleted = false;
        D dto = dtoService.getDtoById(id);
        if (dto != null) {
            isDeleted = dtoService.deleteDto(dto);
        }
        log.info("entity {} with body {} was deleted: {}", entityName, dto, isDeleted);
        return new DeletedBody(isDeleted);
    }

    @GetMapping("/")
    public Object getAll(@RequestHeader HashMap<String, String> httpHeaders) {
        String entityName = parseEntityName();
        log.info("[{}]: /", entityName);
        kafkaProducerService.submit(new KafkaBody("getAll", entityName, null));
        log.info("kafka message was sent");
        return new ListResultBody<>(dtoService.findAllDto());
    }

    protected String parseEntityName() {
        String className = ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]
                .getTypeName();
        String[] packageArray = className.split("\\.");
        return packageArray[packageArray.length-1];
    }
}
