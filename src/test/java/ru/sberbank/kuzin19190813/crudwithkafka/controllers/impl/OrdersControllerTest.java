package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import org.junit.jupiter.api.Test;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.OkBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.TestMapper;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.*;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.OrderDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class OrdersControllerTest extends CrudControllerTest<OrderDTO> {
    private final Long clientId;
    private final Long shopId;

    public OrdersControllerTest() {
        super("/orders", "Order", OrderDTO.class);
        clientId = createClient("Jopj");
        shopId = createShop("Apple");
    }

    @Override
    public OrderDTO constructTestObject() {
        return constructOrder(clientId, shopId);
    }

    @Override
    public CrudControllerTest.ModifiedResult<OrderDTO> modifyTestObject(OrderDTO orderDTO) {
        String updatedValue = Order.Status.IN_DELIVERY.name();
        String old = orderDTO.getStatus();
        orderDTO.setStatus(updatedValue);
        return new CrudControllerTest.ModifiedResult<>(orderDTO, "status", updatedValue, old);
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
    void addProductToOrder() {
        Long productId = createProduct("iPhone 1 mini", shopId);

        Long orderId = saveObject(constructTestObject());

        RespBody respBody = crudRequestExecutor.executeGetRequest(String.format("/%s/products/add/%s", orderId, productId));

        assertEquals(new OkBody(), TestMapper.map(respBody.getResult(), OkBody.class));

        assertNull(respBody.getError());
    }

    @Test
    void getProductsOfOrder() {
        ProductDTO productDTO = constructProduct("iPhone", shopId);

        Long productId = create("/products", productDTO);

        Long orderId = saveObject(constructTestObject());

        crudRequestExecutor.executeGetRequest(String.format("/%s/products/add/%s", orderId, productId));

        RespBody respBody = crudRequestExecutor.executeGetRequest(String.format("/%s/products/", orderId));

        List<ProductDTO> resultProductDtos = TestMapper.mapToDtoList(respBody.getResult(), ProductDTO.class);

        assertEquals(1, resultProductDtos.size());
        assertEquals(productDTO.toString(), resultProductDtos.get(0).toString());
    }
}