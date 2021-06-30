package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = ReviewDTO.class)
public class Review extends AbstractEntity {
    String comment;
    Integer estimate;
    Product product;
    Client client;

    public String getComment() {
        return comment;
    }

    public Integer getEstimate() {
        return estimate;
    }

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    public Product getProduct() {
        return product;
    }

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    public Client getClient() {
        return client;
    }
}
