package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.OrderRepository;

public interface OrderService {
    Order createOrder(Long clientId, Long shopId);
}
