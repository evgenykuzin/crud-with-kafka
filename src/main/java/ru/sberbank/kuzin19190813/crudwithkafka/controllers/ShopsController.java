package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ShopServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@Slf4j
@RestController
@RequestMapping(value = "/shops")
public class ShopsController extends CreatorController<Shop, ShopDTO> {
    private final ShopServiceImpl shopService;

    @Autowired
    public ShopsController(ShopServiceImpl shopService, KafkaProducerServiceImpl kafkaProducerService) {
        super(shopService, kafkaProducerService);
        this.shopService = shopService;
    }
}
