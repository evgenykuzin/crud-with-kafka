package ru.sberbank.kuzin19190813.crudwithkafka.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.ChangeDeliveryStateBody;

@RestController("/api/admin")
public class AdminApiController {

    @PostMapping("/deliveries/state/change")
    public void changeDeliveryState(ChangeDeliveryStateBody changeDeliveryStateBody) {

    }

    @GetMapping("/deliveries/state/{deliveryId}")
    public void getDeliveryState(@PathVariable Long deliveryId) {

    }

}
