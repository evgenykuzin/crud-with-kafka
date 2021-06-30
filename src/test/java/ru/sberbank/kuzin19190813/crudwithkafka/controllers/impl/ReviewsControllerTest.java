package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;

import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class ReviewsControllerTest extends CrudControllerTest<ReviewDTO> {
    private final Long clientId;
    private final Long productId;
    private final Long shopId;
    public ReviewsControllerTest() {
        super("/reviews", "Review", ReviewDTO.class);
        clientId = createClient("Johj");

        shopId = createShop("Apple");

        productId = createProduct("iPhone 25 super max pro", shopId);

    }

    @Override
    public ReviewDTO constructTestObject() {
        return MarketplaceCreator.constructReview(5, clientId, productId);
    }

    @Override
    public ModifiedResult<ReviewDTO> modifyTestObject(ReviewDTO reviewDTO) {
        Integer updatedValue = 2;
        Integer old = reviewDTO.getEstimate();
        reviewDTO.setEstimate(updatedValue);
        return new CrudControllerTest.ModifiedResult<>(reviewDTO, "estimate", updatedValue, old);

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