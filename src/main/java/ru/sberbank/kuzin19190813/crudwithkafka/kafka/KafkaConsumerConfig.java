package ru.sberbank.kuzin19190813.crudwithkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Value(value = "${kafka.server}")
    private String kafkaServer;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaServer);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
//        JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);
//        deserializer.addTrustedPackages(
//                "ru.sberbank.kuzin19190813.crudwithkafka.body",
//                "ru.sberbank.kuzin19190813.crudwithkafka.dto"
//        );
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setErrorHandler(new KafkaErrHandler());
        return factory;
    }

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}", "client-topic"})
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info(
                "Received Message: " + message
                        + "from partition: " + partition);
    }

    @Slf4j
    public static class KafkaErrHandler implements ErrorHandler {
        @Override
        public void handle(Exception e, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer) {
            log.error("Error in process with Exception {} and the record is {}", e, record);
        }

        @Override
        public void handle(Exception e, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
                           MessageListenerContainer container) {
            log.error("Error in process with Exception {} and the records are {}", e, records);
        }

        @Override
        public void handle(Exception e, ConsumerRecord<?, ?> record) {
            log.error("Error in process with Exception {} and the record is {}", e, record);
        }
    }
}