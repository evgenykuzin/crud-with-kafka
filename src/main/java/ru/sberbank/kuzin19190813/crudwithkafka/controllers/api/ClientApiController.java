package ru.sberbank.kuzin19190813.crudwithkafka.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.BuyProductBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.ChooseDeliveryBody;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.ClientServiceImpl;
import ru.sberbank.kuzin19190813.crudwithkafka.services.impl.KafkaProducerServiceImpl;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/client")
public class ClientApiController {
    private static final String clientTopic = "client-topic";

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private KafkaProducerServiceImpl kafkaProducerService;

    @GetMapping
    public String ok() {
        return "OK";
    }


    @PostMapping(value = "/orders/add-product", consumes = { "application/json" }, produces = { "application/json" })
    public BuyProductBody buyProduct(@RequestBody BuyProductBody buyProductBody) throws ExecutionException, InterruptedException {
        System.out.println("buying");
        System.out.println("buyProductBody = " + buyProductBody);
        //Order order = clientService.buyProduct(buyProductBody.getClientId(), buyProductBody.getBarcode());
        //System.out.println("order = " + order);

        kafkaProducerService.submit(buyProductBody);

//        if (order != null) {
//            return buyProductBody;
//        }
        return buyProductBody;
    }

    @PostMapping("/deliveries/choose")
    public void chooseDelivery(@RequestBody ChooseDeliveryBody chooseDeliveryBody) {

        kafkaProducerService.sendMessage(chooseDeliveryBody);
    }

    @DeleteMapping("/orders/cancel/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {

    }

    @PostMapping("/reviews/add")
    public void addReview(ChooseDeliveryBody chooseDeliveryBody) {

    }

    @DeleteMapping("/reviews/delete/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {

    }

    private void submit(Object message) {
        kafkaProducerService.submit(clientTopic, message);
    }
}
