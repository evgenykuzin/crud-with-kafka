package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ClientDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ClientServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@Slf4j
@RestController
@RequestMapping(value = "/clients")
public class ClientsController extends CreatorController<Client, ClientDTO> {
    private final ClientServiceImpl clientService;

    @Autowired
    public ClientsController(ClientServiceImpl clientService, KafkaProducerServiceImpl kafkaProducerService) {
        super(clientService, kafkaProducerService);
        this.clientService = clientService;
    }
}
