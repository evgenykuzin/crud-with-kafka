package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.MessageBody;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.OrderServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController extends CrudController<Order, OrderDTO> {
    @Autowired
    public OrdersController(OrderServiceImpl orderService, KafkaProducerServiceImpl kafkaProducerService) {
        super(orderService, kafkaProducerService);
    }

    @Override
    @PostMapping("/save")
    public Object save(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody OrderDTO dto) {
        if (!Objects.equals(httpHeaders.get("token"), "client-token")) {
            return super.save(httpHeaders, dto);
        }
        kafkaProducerService.submit(new KafkaBody("save", "Order", dto));
        return new MessageBody("error: illegal token");
    }

}
