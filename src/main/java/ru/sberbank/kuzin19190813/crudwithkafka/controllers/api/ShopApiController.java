package ru.sberbank.kuzin19190813.crudwithkafka.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/shop")
public class ShopApiController {

    @GetMapping("/orders")
    public ResponseEntity<String> getOrders() {
        return null; //TODO
    }

    @PostMapping("/orders/accept")
    public void accept() {

    }

    @PostMapping("/products/")
    public void products() {

    }

    @GetMapping("products/{productId}")
    public void getProduct(@PathVariable Long productId) {

    }

    @PostMapping("/products/add")
    public void addProduct() {

    }

    @DeleteMapping("products/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

    }
}
