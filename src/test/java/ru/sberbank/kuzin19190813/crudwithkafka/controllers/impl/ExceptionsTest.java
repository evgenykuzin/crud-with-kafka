package ru.sberbank.kuzin19190813.crudwithkafka.controllers.impl;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.BaseControllerTest;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.util.CrudRequestExecutor;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

public class ExceptionsTest extends BaseControllerTest {
    @Test
    public void exceptionTest() {
        assertThrows(HttpClientErrorException.class, ()->{
            RespBody respBody = CrudRequestExecutor.getInstance("/orders")
                    .save(constructOrder(1L, 1L));
        });
    }
}
