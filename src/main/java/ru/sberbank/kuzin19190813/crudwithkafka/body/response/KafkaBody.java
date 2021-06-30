package ru.sberbank.kuzin19190813.crudwithkafka.body.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class KafkaBody {
    String method;
    String entity;
    Object[] parameters;

    public KafkaBody(String method, String entity, Object... parameters) {
        this.method = method;
        this.entity = entity;
        this.parameters = parameters;
    }
}
