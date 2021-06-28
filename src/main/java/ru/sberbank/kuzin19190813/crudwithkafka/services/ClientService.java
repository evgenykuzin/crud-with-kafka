package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;

public interface ClientService {
    Order buyProduct(Long clientId, Product product);
    Order buyProduct(Long clientId, String barcode);
}
