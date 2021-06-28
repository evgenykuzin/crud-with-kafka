package ru.sberbank.kuzin19190813.crudwithkafka.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto.DtoMapper;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DtoMapper(className = CityDTO.class)
public class City extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    String name;

    public City(String name) {
        this.name = name;
    }

    @Column(name = "city_name")
    public String getName() {
        return name;
    }

}
