package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.OkBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.OrderServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/orders")
public class OrdersController extends CrudController<Order, OrderDTO> {
    OrderServiceImpl orderService;

    @Autowired
    public OrdersController(OrderServiceImpl orderService, KafkaProducerServiceImpl kafkaProducerService) {
        super(orderService, kafkaProducerService);
        this.orderService = orderService;
    }

    @Override
    @PostMapping("/save")
    public RespBody save(@RequestHeader HashMap<String, String> httpHeaders, @RequestBody OrderDTO dto) {
//        if (Objects.equals(httpHeaders.get("token"), "client-token")) {
//            return super.save(httpHeaders, dto);
//        }
//        kafkaProducerService.submit(new KafkaBody("save", "Order", dto));
//        return RespBody.error(new ErrorBody("illegal token"));
        return super.save(httpHeaders, dto);
    }

    @GetMapping("/{orderId}/products/add/{productId}")
    public RespBody<OkBody> addProductToOrder(@RequestHeader HashMap<String, String> httpHeaders,
                               @PathVariable Long orderId,
                               @PathVariable Long productId) {
        String entityName = parseEntityName();
        log.info("[{}]: {}/products/add/{}", entityName, orderId, productId);
        kafkaProducerService.submit(new KafkaBody("addProductToOrder", entityName, orderId, productId));
        log.info("kafka message was sent");
        orderService.addProduct(orderId, productId);
        log.info("product with id:{} added to order with id:{}", productId, orderId);
        return RespBody.result(new OkBody());
    }

    @GetMapping("/{orderId}/products/")
    public RespBody<List<ProductDTO>> getProductsOfOrder(@RequestHeader HashMap<String, String> httpHeaders,
                                                         @PathVariable Long orderId) {
        String entityName = parseEntityName();
        log.info("[{}]: {}/products/", entityName, orderId);
        kafkaProducerService.submit(new KafkaBody("getProductsOfOrder", entityName, orderId));
        log.info("kafka message was sent");
        return RespBody.result(orderService.getProducts(orderId));
    }

}
