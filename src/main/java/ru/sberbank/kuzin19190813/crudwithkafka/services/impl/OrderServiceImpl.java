package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.OrderRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.OrderService;

@Service
public class OrderServiceImpl extends DTOService<OrderDTO, Order, OrderRepository> implements OrderService {

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
    }

    @Override
    public Order createOrder(Long clientId, Long shopId) {
        Order order = new Order();
        Client client = new Client();
        client.setId(clientId);
        order.setClient(client);
        Shop shop = new Shop();
        shop.setId(shopId);
        order.setShop(shop);
        return jpaRepository.save(order);
    }
}
