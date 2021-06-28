package ru.sberbank.kuzin19190813.crudwithkafka.services;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.Consumer;

public interface KafkaProducerService {
    ListenableFuture<SendResult<String, String>> sendMessage(String topic, Object message);
    ListenableFuture<SendResult<String, String>> sendMessage(Object message);
    void submit(String topic, Object message, Consumer<ListenableFuture<SendResult<String, String>>> futureConsumer);
    void submit(String topic, Object message);
    void submit(Object message, Consumer<ListenableFuture<SendResult<String, String>>> futureConsumer);
    void submit(Object message);
}
