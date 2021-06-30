package ru.sberbank.kuzin19190813.crudwithkafka.controllers.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.RequestExecutor;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class BaseControllerTestRequestExecutor extends RequestExecutor {

    public BaseControllerTestRequestExecutor(String baseEndpoint) {
        super(baseEndpoint);
    }

    public RespBody test(Supplier<RespBody> responseSupplier, boolean failOnErrors) {
        RespBody respBody = responseSupplier.get();
        checkErrors(failOnErrors, respBody);
        log.info("respBody = " + respBody);
        Assertions.assertNotNull(respBody);
        return respBody;
    }

    public void checkErrors(boolean failOnErrors, RespBody respBody) {
        if (failOnErrors) {
            if (respBody.getError() != null) {
                Assertions.fail(respBody.getError().toString());
            }
        }
    }

}
