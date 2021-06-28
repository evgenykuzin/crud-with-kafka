package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.OkBody;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.AbstractDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.services.CreatorService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@Slf4j
public abstract class CreatorController<E extends AbstractEntity, D extends AbstractDTO> extends CrudController<E, D>{
    protected CreatorService<D, E, ? extends JpaRepository<E, Long>> creatorService;

    public CreatorController(CreatorService<D, E, ? extends JpaRepository<E, Long>> dtoService, KafkaProducerServiceImpl kafkaProducerService) {
        super(dtoService, kafkaProducerService);
        this.creatorService = dtoService;
    }

    @PostMapping("/create")
    public Object create(@RequestParam String name) {
        String entityName = parseEntityName();
        log.info("[{}}]: /create/?name={}", entityName, name);
        kafkaProducerService.submit(new KafkaBody("create", entityName, name));
        log.info("kafka message was sent");
        Long id = creatorService.create(name);
        log.info("{} created with id: {}", entityName, id);
        return new OkBody();
    }
}
