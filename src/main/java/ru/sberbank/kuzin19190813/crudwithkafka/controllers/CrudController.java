package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.*;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.AbstractBaseRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

@Slf4j
public abstract class CrudController<E extends AbstractEntity, D extends AbstractDTO> {
    protected DTOService<D, E, ? extends AbstractBaseRepository<E>> dtoService;
    protected KafkaProducerServiceImpl kafkaProducerService;

    public CrudController(DTOService<D, E, ? extends JpaRepository<E, Long>> dtoService, KafkaProducerServiceImpl kafkaProducerService) {
        this.dtoService = dtoService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/get/{id}")
    public RespBody<D> get(@RequestHeader HashMap<String, String> httpHeaders, @PathVariable Long id) {
        String entityName = parseEntityName();
        log.info("[{}]: /get/{}", entityName, id);
        kafkaProducerService.submit(new KafkaBody("get", entityName, id));
        log.info("kafka message was sent");
        D dto = dtoService.getDtoById(id);
        if (dto == null) {
            return RespBody.error(new NotFoundBody(entityName, id));
        }
        return RespBody.result(dto);
    }

    @PostMapping("/save")
    public RespBody<Long> save(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody D dto) {
        String entityName = parseEntityName();
        log.info("[{}]: /save {}", entityName, dto);
        kafkaProducerService.submit(new KafkaBody("save", entityName, dto));
        log.info("kafka message was sent");
        Long id = dtoService.saveDto(dto);
        log.info("saved entity id: {}", id);
        return RespBody.result(id);
    }

    @PostMapping("/update")
    public RespBody<D> update(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody D dto) {
        String entityName = parseEntityName();
        log.info("[{}]: /update {}", entityName, dto);
        kafkaProducerService.submit(new KafkaBody("update", entityName, dto));
        log.info("kafka message was sent");
        if (dto.getId() == null) return RespBody.error(new NotFoundBody(entityName, null));
        dtoService.saveDto(dto);
        log.info("{} updated with body: {}", entityName, dto);
        return RespBody.result(dto);
    }

    @GetMapping("/delete/{id}")
    public RespBody<DeletedBody> delete(@RequestHeader HashMap<String, String> httpHeaders, @PathVariable Long id) {
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
        return RespBody.result(new DeletedBody(isDeleted));
    }

    @GetMapping("/")
    public RespBody<List<D>> findAll(@RequestHeader HashMap<String, String> httpHeaders) {
        String entityName = parseEntityName();
        log.info("[{}]: /", entityName);
        kafkaProducerService.submit(new KafkaBody("findAll", entityName));
        log.info("kafka message was sent");
        return RespBody.result(dtoService.findAllDto());
    }

    @GetMapping("/clear-all")
    public RespBody<OkBody> dropAll(@RequestHeader HashMap<String, String> httpHeaders) {
        String entityName = parseEntityName();
        log.info("[{}]: /clear-all", entityName);
        kafkaProducerService.submit(new KafkaBody("clearAll", entityName));
        log.info("kafka message was sent");
        dtoService.clearAll();
        return RespBody.result(new OkBody());
    }

    protected String parseEntityName() {
        String className = ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]
                .getTypeName();
        String[] packageArray = className.split("\\.");
        return packageArray[packageArray.length-1];
    }
}
