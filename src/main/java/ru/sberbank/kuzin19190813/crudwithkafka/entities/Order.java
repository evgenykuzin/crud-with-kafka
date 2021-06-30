package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true, exclude = {"client", "shop", "delivery"})
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "m_order")
@DtoMapper(className = OrderDTO.class)
public class Order extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    @Enumerated(EnumType.STRING)
    Status status = Status.IN_DELIVERY;
    Client client;
    Shop shop;
    Delivery delivery;
    List<Product> products;

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

    @ManyToOne
    @JoinColumn(name="delivery_id")
    public Delivery getDelivery() {
        return delivery;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "m_orders_products",
            joinColumns = @JoinColumn(name = "m_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public enum Status {
        IN_PROCESS,
        IN_DELIVERY,
        DONE
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", client=" + client.getId() +
                ", shop=" + shop.getId() +
                ", delivery=" + delivery.getId() +
                '}';
    }
}
