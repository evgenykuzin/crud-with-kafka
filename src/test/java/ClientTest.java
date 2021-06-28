import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.BuyProductBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.api.ClientApiController;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApiController.class)
public class ClientTest {

    @Autowired
    public ClientApiController controller;

    @Test
    public void pingTest() {
        String actual = controller.ok();
        Assertions.assertEquals("OK", actual, "Method ping() always return OK");
    }

    @Test
    public void testBuyProduct() throws ExecutionException, InterruptedException {
        BuyProductBody actual = controller.buyProduct(null);
        Assertions.assertEquals("OK", actual.toString(), "Method ping() always return OK");
    }
}
