package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "m_order")
@DtoMapper(className = OrderDTO.class)
public class Order extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    Status status;
    Client client;
    Shop shop;
    List<Product> products;
    Delivery delivery;

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    public Client getClient() {
        return client;
    }

    @ManyToOne
    @JoinColumn(name="shop_id", nullable=false)
    public Shop getShop() {
        return shop;
    }

    @ManyToMany
    @JoinTable(
            name = "m_orders_products",
            joinColumns = @JoinColumn(name = "m_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    @ManyToOne
    @JoinColumn(name="delivery_id")
    public Delivery getDelivery() {
        return delivery;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public enum Status {
        IN_PROCESS,
        IN_DELIVERY,
        DONE
    }
}
