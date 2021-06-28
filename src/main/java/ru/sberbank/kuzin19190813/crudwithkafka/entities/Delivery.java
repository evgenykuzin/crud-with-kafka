package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.DeliveryDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = DeliveryDTO.class)
public class Delivery extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    City city;
    Date date;
    Method method;
    List<Order> orders;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    public City getCity() {
        return city;
    }

    @DateTimeFormat(pattern = "yy:mm:dd")
    @Column(name = "arrive_date")
    public Date getDate() {
        return date;
    }

    @Enumerated(EnumType.STRING)
    //\@ColumnDefault("COURIER")
    @Column(name = "method")
    public Method getMethod() {
        return method;
    }

    @OneToMany(mappedBy = "delivery")
    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public enum Method {
        COURIER,
        SHIP,
        TRAIN,
        PLAIN
    }

    public enum Status {
        PACKAGING,
        DELIVERING,
        ARRIVED
    }
}
