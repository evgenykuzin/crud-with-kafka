package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;

public interface ProductService {
    Product findByBarcode(String barcode);
}
