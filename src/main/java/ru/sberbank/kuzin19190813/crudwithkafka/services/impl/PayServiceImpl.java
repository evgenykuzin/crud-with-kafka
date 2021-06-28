package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.body.request.PaymentBody;
import ru.sberbank.kuzin19190813.crudwithkafka.services.PayService;

@Service
public class PayServiceImpl implements PayService {

    @Override
    public boolean checkPayment(PaymentBody paymentBody) {
        return true;
    }

}
