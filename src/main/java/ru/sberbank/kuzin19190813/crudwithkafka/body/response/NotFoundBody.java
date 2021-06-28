package ru.sberbank.kuzin19190813.crudwithkafka.body.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotFoundBody implements Serializable {
    String message;

    public NotFoundBody(String entityName, Long id) {
        message = String.format("%s with id %d not found", entityName, id);
    }
}
