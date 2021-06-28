package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ClientDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ClientRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.*;

import java.util.List;

@Service
//@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientServiceImpl extends CreatorService<ClientDTO, Client, ClientRepository> implements ClientService {
    ProductServiceImpl productService;
    OrderServiceImpl ordersService;
    DeliveryServiceImpl deliveryService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ProductServiceImpl productService, OrderServiceImpl ordersService, DeliveryServiceImpl deliveryService) {
        super(clientRepository);
        this.productService = productService;
        this.ordersService = ordersService;
        this.deliveryService = deliveryService;
    }

    @Override
    protected Client getNew(String name) {
        return new Client(name);
    }

    @Override
    public Order buyProduct(Long clientId, String barcode) {
        Product product = productService.findByBarcode(barcode);
        return buyProduct(clientId, product);
    }

    @Override
    public Order buyProduct(Long clientId, Product product) {
        Order order;
        List<Order> clientOrders = ordersService.findBy("clientId", clientId);
        List<Delivery> clientDeliveries = deliveryService.findBy("clientId", clientId);
        if ((clientOrders == null || clientDeliveries == null) || clientOrders.isEmpty() & clientDeliveries.isEmpty()) {

            order = ordersService.createOrder(clientId, 1L);
        } else {
            for (Delivery delivery : clientDeliveries) {
                for (Order orderInDelivery : delivery.getOrders()) {
                    clientOrders.remove(orderInDelivery);
                }
            }
            if (clientOrders.size() > 1) {
                if (clientOrders.stream().allMatch(o -> o.getStatus().equals(Order.Status.IN_PROCESS))) {
                    throw new IllegalArgumentException("It can be only one order with status 'IN_PROCESS'");
                } else {
                    Order illegalOrder = clientOrders.stream().filter(o -> o.getStatus().equals(Order.Status.IN_DELIVERY)).findFirst().orElse(null);
                    if (illegalOrder != null) {
                        throw new IllegalArgumentException(String.format("Order (%s) mapped with Delivery (%s), but that Delivery not mapped with that Order", illegalOrder, illegalOrder.getDelivery()));
                    }
                }
            }
            order = clientOrders.get(0);
        }

//        order.addProduct(product);
//        product.addInOrder(order);

        return order;
    }


}
