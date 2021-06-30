package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import org.junit.jupiter.api.Test;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.CrudControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.TestMapper;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

class ClientsControllerTest extends CrudControllerTest<ClientDTO> {

    public ClientsControllerTest() {
        super("/clients", "Client", ClientDTO.class);
    }

    @Override
    public ClientDTO constructTestObject() {
        return MarketplaceCreator.constructClient("Jorj");
    }

    @Override
    public ModifiedResult<ClientDTO> modifyTestObject(ClientDTO clientDTO) {
        String updatedValue = "Joubj";
        String old = clientDTO.getClientName();
        clientDTO.setClientName(updatedValue);
        return new ModifiedResult<>(clientDTO, "clientName", updatedValue, old);
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
    public void addAndGetOrdersTest() {
        Long clientId = createClient("Jokj");

        Long shopId = createShop("Apple");

        Long orderId = createOrder(clientId, shopId);

        System.out.println("orderId = " + orderId);

        RespBody respBody = crudRequestExecutor.executeGetRequest("/" + clientId + "/orders/");
        System.out.println("respBody = " + respBody);

        List<OrderDTO> orderDTOList = TestMapper.mapToDtoList(respBody.getResult(), OrderDTO.class);

        assertEquals(1, orderDTOList.size());

        OrderDTO orderDTO = orderDTOList.get(0);

        assertEquals(orderId, orderDTO.getId());
    }
}