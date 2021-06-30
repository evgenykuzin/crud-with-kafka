package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import org.junit.jupiter.api.Test;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class ShopsControllerTest extends CrudControllerTest<ShopDTO> {
    private Long clientId;
    private List<ProductDTO> productDTOList = new ArrayList<>();
    private ProductDTO product1;
    private ProductDTO product2;
    public ShopsControllerTest() {
        super("/shops", "Shop", ShopDTO.class);

        clientId = createClient("Junj");

    }

    @Override
    public ShopDTO constructTestObject() {
        return constructShop("Apple");
    }

    @Override
    public ModifiedResult<ShopDTO> modifyTestObject(ShopDTO shopDTO) {
        Double updatedValue = 5.0;
        Double old = shopDTO.getRating();
        shopDTO.setRating(updatedValue);
        return new CrudControllerTest.ModifiedResult<>(shopDTO, "rating", updatedValue, old);

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
    public void createAndGetProductTest() {
        Long shopId = saveObject(constructTestObject());

        productDTOList = new ArrayList<>();
        product1 = constructProduct("iPhone 50 insane pro super max", shopId);
        product2 = constructProduct("iPhone 50 mini", shopId);
        productDTOList.add(product1);
        productDTOList.add(product2);

        crudRequestExecutor.executePostRequest("/"+shopId+"/products/create", product1);
        crudRequestExecutor.executePostRequest("/"+shopId+"/products/create", product2);

        RespBody respBody = crudRequestExecutor.executeGetRequest("/"+shopId+"/products");

        assertNull(respBody.getError());
    }
}