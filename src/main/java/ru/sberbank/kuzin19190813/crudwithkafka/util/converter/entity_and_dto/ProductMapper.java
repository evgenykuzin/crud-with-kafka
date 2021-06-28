package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ProductDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.AbstractEntity;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Product;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.OrderRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ShopRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDTO> {
    private final ModelMapper mapper;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ProductMapper(ModelMapper mapper, ShopRepository shopRepository, OrderRepository orderRepository) {
        super(mapper, Product.class, ProductDTO.class);
        this.mapper = mapper;
        this.shopRepository = shopRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Product.class, ProductDTO.class)
                .addMappings(m -> m.skip(ProductDTO::setShopId))
                .addMappings(m -> m.skip(ProductDTO::setOrderIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProductDTO.class, Product.class)
                .addMappings(m -> m.skip(Product::setShop))
                .addMappings(m -> m.skip(Product::setOrders))
                .setPostConverter(toEntityConverter());
        }

    @Override
    void mapSpecificFields(Product source, ProductDTO destination) {
        destination.setShopId(getShopId(source));
        destination.setOrderIds(getOrderIds(source));
    }

    private Long getShopId(Product source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getShop().getId();
    }

    private List<Long> getOrderIds(Product source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source
                .getOrders()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    void mapSpecificFields(ProductDTO source, Product destination) {
        destination.setShop(shopRepository.findById(source.getShopId()).orElse(null));
        destination.setOrders(orderRepository.findAllById(source.getOrderIds()));
    }
}
