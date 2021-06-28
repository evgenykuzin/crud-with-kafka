package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.City;

import static org.junit.jupiter.api.Assertions.*;

class CitiesControllerTest extends CrudControllerTest<CityDTO> {


    public CitiesControllerTest() {
        super("/cities");
    }

    @Test
    void get() {
        super.get(6L);
    }

    @Test
    void save() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Tver");
        super.save(cityDTO);
    }
}