package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ShopRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.CreatorService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.ShopService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl extends CreatorService<ShopDTO, Shop, ShopRepository> implements ShopService {

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        super(shopRepository);
    }

    @Override
    protected Shop getNew(String name) {
        return new Shop(name);
    }

    @Override
    public Long createProduct(Long shopId, ProductDTO productDTO) {
        Shop shop = repository.getById(shopId);
        Product product = superMapper.toEntity(productDTO);
        shop.getProducts().add(product);
        save(shop);
        return product.getId();
    }

    @Override
    public List<ProductDTO> getProducts(Long shopId) {
        return getById(shopId)
                .getProducts()
                .stream()
                .map(product -> (ProductDTO) superMapper.toDto(product))
                .collect(Collectors.toList());
    }
}
