package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ClientDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.DeliveryDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ClientRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.DeliveryRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DeliveryService;

@Service
public class DeliveryServiceImpl extends DTOService<DeliveryDTO, Delivery, DeliveryRepository> implements DeliveryService {
    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        super(deliveryRepository);
    }

    @Override
    public Delivery createDelivery() {
        Delivery delivery = new Delivery();
        jpaRepository.save(delivery);
        return delivery;
    }

    @Override
    public void addToDelivery(Long deliveryId, Order order) {
        jpaRepository.findById(deliveryId).ifPresent(delivery -> {
            delivery.addOrder(order);
            jpaRepository.save(delivery);
        });
    }
}
