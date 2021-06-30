package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;

public interface OrderService {
    Order createOrder(Long clientId, Long shopId);
    void addProduct(Long orderId, Long productId);

}
