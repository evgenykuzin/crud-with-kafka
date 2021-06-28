package ru.sberbank.kuzin19190813.crudwithkafka.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.server}")
    private String kafkaServer;
    @Value(value = "${spring.kafka.template.default-topic}")
    private String defaultTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic msgTopic() {
        return TopicBuilder.name(defaultTopic)
                .partitions(10)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic clientTopic(){
        return newTopic("client-topic");
    }

    @Bean
    public NewTopic shopTopic(){
        return newTopic("shop-topic");
    }

    @Bean
    public NewTopic adminTopic(){
        return newTopic("admin-topic");
    }

    public static NewTopic newTopic(String name) {
        return TopicBuilder.name(name)
                .partitions(10)
                .replicas(2)
                .build();
    }
}