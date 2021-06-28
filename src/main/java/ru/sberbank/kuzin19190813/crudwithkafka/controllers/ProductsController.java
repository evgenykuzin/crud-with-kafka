package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ProductServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

@RestController
@RequestMapping(value = "/products")
public class ProductsController extends CrudController<Product, ProductDTO> {
    @Autowired
    public ProductsController(ProductServiceImpl productService, KafkaProducerServiceImpl kafkaProducerService) {
        super(productService, kafkaProducerService);
    }
}
