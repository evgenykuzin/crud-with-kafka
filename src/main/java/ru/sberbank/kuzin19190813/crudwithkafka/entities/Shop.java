package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"products", "orders"})
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = ShopDTO.class)
public class Shop extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    String shopName;
    Double rating;
    List<Product> products;
    List<Order> orders;

    public Shop(String shopName) {
        this.shopName = shopName;
        this.rating = 0.0;
    }

    @Column(name = "shop_name")
    public String getShopName() {
        return shopName;
    }

    public Double getRating() {
        return rating;
    }

    @OneToMany(mappedBy = "shop")
    public List<Product> getProducts() {
        return products;
    }

    @OneToMany(mappedBy = "shop")
    public List<Order> getOrders() {
        return orders;
    }
}
