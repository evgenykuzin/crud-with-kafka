package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.entity_and_dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Review;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ClientRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ReviewMapper extends AbstractMapper<Review, ReviewDTO> {
    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ReviewMapper(ModelMapper mapper, ProductRepository productRepository, ClientRepository clientRepository) {
        super(mapper, Review.class, ReviewDTO.class);
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Review.class, ReviewDTO.class)
                .addMappings(m -> m.skip(ReviewDTO::setProductId))
                .addMappings(m -> m.skip(ReviewDTO::setClientId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ReviewDTO.class, Review.class)
                .addMappings(m -> m.skip(Review::setProduct))
                .addMappings(m -> m.skip(Review::setClient))
                .setPostConverter(toEntityConverter());
        }

    @Override
    void mapSpecificFields(Review source, ReviewDTO destination) {
        destination.setProductId(getProductId(source));
        destination.setClientId(getClientId(source));
    }

    private Long getProductId(Review source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProduct().getId();
    }

    private Long getClientId(Review source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getClient().getId();
    }

    @Override
    void mapSpecificFields(ReviewDTO source, Review destination) {
        destination.setProduct(productRepository.findById(source.getProductId()).orElse(null));
        destination.setClient(clientRepository.findById(source.getClientId()).orElse(null));
    }
}
