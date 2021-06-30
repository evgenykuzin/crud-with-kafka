package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.KafkaBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ShopServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.util.HashMap;
import java.util.List;

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

    @PostMapping("/{shopId}/products/create")
    public RespBody<Long> createProductInShop(@RequestHeader HashMap<String, String> httpHeaders,
                                      @PathVariable Long shopId,
                                      @RequestBody ProductDTO productDTO) {
        String entityName = parseEntityName();
        log.info("[{}]: {}/products/add {}", entityName, shopId, productDTO);
        kafkaProducerService.submit(new KafkaBody("createProductInShop", entityName, shopId, productDTO));
        log.info("kafka message was sent");
        Long productId = shopService.createProduct(shopId, productDTO);
        log.info("product {} added in shop with id: {}", productDTO, shopId);
        return RespBody.result(productId);
    }

    @GetMapping("/{shopId}/products")
    public RespBody<List<ProductDTO>> getProductsOfShop(@RequestHeader HashMap<String, String> httpHeaders,
                                                        @PathVariable Long shopId) {
        String entityName = parseEntityName();
        log.info("[{}]: {}/products/", entityName, shopId);
        kafkaProducerService.submit(new KafkaBody("getProductsOfShop", entityName, shopId));
        log.info("kafka message was sent");
        return RespBody.result(shopService.getProducts(shopId));
    }
}
