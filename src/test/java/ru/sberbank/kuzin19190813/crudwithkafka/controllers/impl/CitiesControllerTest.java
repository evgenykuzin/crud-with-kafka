package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import org.junit.jupiter.api.Test;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.TestMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class CitiesControllerTest extends CrudControllerTest<CityDTO> {

    public CitiesControllerTest() {
        super("/cities", "City", CityDTO.class);
    }

    @Override
    public CityDTO constructTestObject() {
        return constructCity("Moscow");
    }

    @Override
    public ModifiedResult<CityDTO> modifyTestObject(CityDTO cityDTO) {
        String updatedValue = "Chelyabinsk";
        String old = cityDTO.getName();
        cityDTO.setName(updatedValue);
        return new ModifiedResult<>(cityDTO, "name", updatedValue, old);
    }

    @Override
    public void testsForGet() {

    }

    @Override
    public void testsForSave() {

    }

    @Override
    public void testsForUpdate() {

    }

    @Override
    public void testsForDelete() {

    }

    @Override
    public void testsForFindAll() {

    }

    @Test
    @Override
    protected void findAll() {
        List<CityDTO> createdItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            createdItems.add(constructTestObject());
        }
        createdItems.forEach(crudRequestExecutor::save);

        RespBody respBody = crudRequestExecutor.findAll();
        List<CityDTO> foundedItems = TestMapper.mapToDtoList(respBody.getResult(), dtoClass);
        assertEquals(Collections.singletonList(constructTestObject()).toString(), foundedItems.toString());
        testsForFindAll();
        crudRequestExecutor.clearAll();
    }
}