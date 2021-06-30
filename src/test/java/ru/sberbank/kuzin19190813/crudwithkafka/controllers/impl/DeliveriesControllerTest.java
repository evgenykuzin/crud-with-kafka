package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.util.CrudRequestExecutor;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.DeliveryDTO;

import java.util.Date;
import java.util.Random;

import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

public class DeliveriesControllerTest extends CrudControllerTest<DeliveryDTO> {
   // private final Long cityId;

    public DeliveriesControllerTest() {
        super("/deliveries", "Delivery", DeliveryDTO.class);
        //cityId = createCity("Omsk");
    }

    @Override
    public DeliveryDTO constructTestObject() {
        Long cityId = createCity("City"+new Random().nextInt());
        return constructDelivery(cityId);
    }

    @Override
    public ModifiedResult<DeliveryDTO> modifyTestObject(DeliveryDTO deliveryDTO) {
        Date updatedValue = new Date();
        Date old = deliveryDTO.getDate();
        deliveryDTO.setDate(updatedValue);
        return new ModifiedResult<>(deliveryDTO, "date", updatedValue, old);
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

}