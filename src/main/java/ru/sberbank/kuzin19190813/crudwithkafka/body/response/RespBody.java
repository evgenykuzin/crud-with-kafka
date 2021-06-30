package ru.sberbank.kuzin19190813.crudwithkafka.body.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RespBody<R> {
    R result;
    Object error;

    public static <R> RespBody<R> result(R result){
        return new RespBody<>(result, null);
    }

    public static <R> RespBody<R> error(Object error){
        return new RespBody<>(null, error);
    }

}
