package ru.sberbank.kuzin19190813.crudwithkafka.util.test;

import ru.sberbank.kuzin19190813.crudwithkafka.dto.*;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Delivery;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Order;

import java.util.Date;
import java.util.Random;

public class MarketplaceCreator {
    public static void createMarketplace1() {
        Long moscowId = createCity("Moscow");
        Long londonId = createCity("London");

        Long appleId = createShop("Apple");
        Long sneakersId = createShop("Sneakers");

        Long client1Id = createClient("Jobj");
        Long client2Id = createClient("Juj");
        Long client3Id = createClient("Jjon");
        Long client4Id = createClient("Jib");

        Long iphon12Id = createProduct("iPhone 12 pro", appleId);
        Long iphon20Id = createProduct("iPhone 20 max super pro", appleId);
        Long iphon50Id = createProduct("iPhone 50 insane mega pro max", appleId);
        Long iphon200Id = createProduct("iPhone 200 EE", appleId);

        Long nike1Id = createProduct("Nike Air Force", sneakersId);
        Long adidas1Id = createProduct("Simple Adidas", sneakersId);

        Long deliveryMoscow1Id = createDelivery(moscowId);
        Long deliveryMoscow2Id = createDelivery(moscowId);
        Long deliveryLondonId = createDelivery(londonId);

        Long review1Id = createReview(5, client1Id, iphon12Id);
        Long review2Id = createReview(1, client2Id, iphon12Id);

        Long order1Id = createOrder(client1Id, appleId);
        Long order2Id = createOrder(client2Id, appleId);
        Long order3Id = createOrder(client1Id, sneakersId);
    }

    public static <D> Long create(String endpoint, D obj) {
        return TestMapper.mapToLong(new RequestExecutor(endpoint)
                .executePostRequest("/save/", obj).getResult());
    }

    public static Long createShop(String name) {
        return create("/shops", constructShop(name));
    }

    public static Long createClient(String name) {
        return create("/clients", constructClient(name));
    }

    public static Long createCity(String name) {
        return create("/cities", constructCity(name));
    }

    public static Long createProduct(String name, Long shopId) {
        return create("/products", constructProduct(name, shopId));
    }

    public static Long createOrder(Long clientId, Long shopId) {
        return create("/orders", constructOrder(clientId, shopId));
    }

    public static Long createDelivery(Long cityId) {
        return create("/deliveries", constructDelivery(cityId));
    }

    public static Long createReview(Integer estimate, Long clientId, Long productId) {
        return create("/reviews", constructReview(estimate, clientId, productId));
    }

    public static ShopDTO constructShop(String name) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName(name);
        shopDTO.setRating(4.0);
        return shopDTO;
    }

    public static ClientDTO constructClient(String name) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName(name);
        return clientDTO;
    }

    public static CityDTO constructCity(String name) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(name);
        return cityDTO;
    }

    public static ProductDTO constructProduct(String name, Long shopId) {
        Random random = new Random();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(name);
        productDTO.setPrice(random.nextDouble());
        productDTO.setBarcode("barcode"+random.nextInt());
        productDTO.setArticle("article"+random.nextInt());
        productDTO.setShopId(shopId);
        return productDTO;
    }

    public static OrderDTO constructOrder(Long clientId, Long shopId) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClientId(clientId);
        orderDTO.setShopId(shopId);
        orderDTO.setDeliveryId(null);
        orderDTO.setStatus(Order.Status.IN_PROCESS.name());
        return orderDTO;
    }

    public static DeliveryDTO constructDelivery(Long cityId) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setMethod(Delivery.Method.TRAIN.name());
        deliveryDTO.setDate(new Date(new Date().getTime() + new Random().nextLong()));
        deliveryDTO.setCityId(cityId);
        return deliveryDTO;
    }

    public static ReviewDTO constructReview(Integer estimate, Long clientId, Long productId) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setEstimate(estimate);
        String comment = "good";
        if (estimate < 5) comment = "bad";
        reviewDTO.setComment(comment + " product, bro");
        reviewDTO.setClientId(clientId);
        reviewDTO.setProductId(productId);
        return reviewDTO;
    }


}
