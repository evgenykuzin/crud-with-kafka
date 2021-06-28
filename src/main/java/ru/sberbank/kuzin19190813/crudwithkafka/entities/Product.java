package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = ProductDTO.class)
public class Product extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    String productName;
    Double price;
    String barcode;
    String article;
    Shop shop;
    List<Order> orders;

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getArticle() {
        return article;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    public Shop getShop() {
        return shop;
    }

    @ManyToMany(mappedBy = "products")
    public List<Order> getOrders() {
        return orders;
    }

    public void addInOrder(Order order) {
        orders.add(order);
    }
}
