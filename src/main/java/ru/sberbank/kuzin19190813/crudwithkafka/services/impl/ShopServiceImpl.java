package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ShopDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Shop;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ShopRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.CreatorService;
import ru.sberbank.kuzin19190813.crudwithkafka.services.ShopService;

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
}
