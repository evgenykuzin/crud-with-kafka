package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.DeliveryDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.DeliveryServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveriesController extends CrudController<Delivery, DeliveryDTO> {
    @Autowired
    public DeliveriesController(DeliveryServiceImpl deliveryService, KafkaProducerServiceImpl kafkaProducerService) {
        super(deliveryService, kafkaProducerService);
    }
}
