package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.DeliveryRepository;

public interface DeliveryService {
    Delivery createDelivery();
    void addToDelivery(Long deliveryId, Order order);
}
