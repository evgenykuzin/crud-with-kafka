package ru.sberbank.kuzin19190813.crudwithkafka.body.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class KafkaBody {
    String method;
    String entity;
    Object parameter;
}
