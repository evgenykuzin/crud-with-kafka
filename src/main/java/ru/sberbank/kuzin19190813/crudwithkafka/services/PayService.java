package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.body.request.PaymentBody;

public interface PayService {
    boolean checkPayment(PaymentBody paymentBody);
}
