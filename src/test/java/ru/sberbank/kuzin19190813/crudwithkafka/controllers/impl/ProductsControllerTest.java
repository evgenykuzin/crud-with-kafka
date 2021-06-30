package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;

import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class ProductsControllerTest extends CrudControllerTest<ProductDTO> {
    Long shopId;

    public ProductsControllerTest() {
        super("/products", "Product", ProductDTO.class);
        shopId = createShop("Apple");
    }

    @Override
    public ProductDTO constructTestObject() {
        return constructProduct("iPhone 20 max mega super pro", shopId);
    }

    @Override
    public ModifiedResult<ProductDTO> modifyTestObject(ProductDTO productDTO) {
        Double updatedValue = 6500.0;
        Double old = productDTO.getPrice();
        productDTO.setPrice(updatedValue);
        return new CrudControllerTest.ModifiedResult<>(productDTO, "price", updatedValue, old);

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