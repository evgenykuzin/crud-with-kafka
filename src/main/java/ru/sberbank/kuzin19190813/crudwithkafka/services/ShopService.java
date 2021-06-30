package ru.sberbank.kuzin19190813.crudwithkafka.services;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;

import java.util.List;

public interface ShopService {
    Long createProduct(Long shopId, ProductDTO productDTO);
    List<ProductDTO> getProducts(Long shopId);
}
