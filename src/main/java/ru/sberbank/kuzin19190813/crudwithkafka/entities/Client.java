package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ClientDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"orders"})
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = ClientDTO.class)
public class Client extends AbstractEntity {
    String clientName;
    List<Review> reviews;
    List<Order> orders;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    @Column(name = "client_name", nullable = false)
    public String getClientName() {
        return clientName;
    }

    @OneToMany(mappedBy = "client")
    public List<Review> getReviews() {
        return reviews;
    }

    @OneToMany(mappedBy = "client")
    public List<Order> getOrders() {
        return orders;
    }
}
