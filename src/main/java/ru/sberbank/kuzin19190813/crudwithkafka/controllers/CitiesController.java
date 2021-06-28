package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.City;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.CityServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@RestController
@RequestMapping(value = "/cities")
public class CitiesController extends CreatorController<City, CityDTO> {
    @Autowired
    public CitiesController(CityServiceImpl cityService, KafkaProducerServiceImpl kafkaProducerService) {
        super(cityService, kafkaProducerService);
    }
}
