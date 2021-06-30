package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLInsert;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = CityDTO.class)
@SQLInsert(sql="INSERT IGNORE INTO CITY(created,city_name,id) VALUES(?,?,?)")
public class City extends AbstractEntity {
    String name;
    List<Delivery> deliveries;

    public City(String name) {
        this.name = name;
    }

    @Column(name = "city_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
