package ru.sberbank.kuzin19190813.crudwithkafka.body.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ChooseDeliveryBody implements Serializable {
    Long orderId;
    Long cityId;
    @DateTimeFormat(pattern = "yy:MM:dd")
    Date date;
}
