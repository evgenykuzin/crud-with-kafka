package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.sberbank.kuzin19190813.crudwithkafka.services.KafkaProducerService;
import ru.sberbank.kuzin19190813.crudwithkafka.util.converter.json.JsonConverterImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    @Value(value = "${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonConverterImpl jsonConverter;
    private final ExecutorService executorService;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate, JsonConverterImpl jsonConverter) {
        this.kafkaTemplate = kafkaTemplate;
        this.jsonConverter = jsonConverter;
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public ListenableFuture<SendResult<String, String>> sendMessage(Object message) {
        return sendMessage(topic, message);
    }

    @Override
    public void submit(String topic, Object message, Consumer<ListenableFuture<SendResult<String, String>>> listenableFutureConsumer) {
        executorService.execute(()-> listenableFutureConsumer.accept(sendMessage(topic, message)));
    }

    @Override
    public void submit(String topic, Object message) {
        submit(topic, message, future -> {
            try {
                log.info(future.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void submit(Object message, Consumer<ListenableFuture<SendResult<String, String>>> listenableFutureConsumer) {
        submit(topic, message, listenableFutureConsumer);
    }

    @Override
    public void submit(Object message) {
        submit(topic, message);
    }

    @Override
    public ListenableFuture<SendResult<String, String>> sendMessage(String topic, Object message) {
        String jsonMessage = jsonConverter.convertToString(message);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, jsonMessage);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(@NotNull Throwable ex) {
                log.error("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
        return future;
    }
}
