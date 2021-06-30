package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.OrderRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ProductRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends DTOService<OrderDTO, Order, OrderRepository> implements OrderService {
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        super(orderRepository);
        this.productRepository = productRepository;
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
        return repository.save(order);
    }

    public void addProduct(Long orderId, Long productId) {
        Order order = repository.getById(orderId);
        Product product = productRepository.getById(productId);
        if (!order.getShop().getId().equals(product.getShop().getId())) {
            throw new IllegalArgumentException("shopId is not equals in order and product");
        }
        order.addProduct(product);
        repository.save(order);
    }

    public List<ProductDTO> getProducts(Long orderId) {
        return getById(orderId)
                .getProducts()
                .stream()
                .map(product -> (ProductDTO) superMapper.toDto(product))
                .collect(Collectors.toList());
    }
}
