package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ProductRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.DTOService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.ProductService;

@Service
//@NoArgsConstructor
public class ProductServiceImpl extends DTOService<ProductDTO, Product, ProductRepository> implements ProductService {
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public Product findByBarcode(String barcode) {
        return findBy("barcode", barcode).stream().findFirst().orElse(null);
    }
}
