package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonConverterImpl implements JsonConverter {
    private final ObjectMapper objectMapper;

    @Autowired
    public JsonConverterImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> String convertToString(T t) {
        return convert(() -> objectMapper.writeValueAsString(t), "{}");
    }

    @Override
    public JsonNode convertToJson(String s) {
        return convert(() -> objectMapper.readTree(s), emptyJsonNode());
    }

    @Override
    public <T> JsonNode convertToJson(T t) {
        return convert(() -> objectMapper.readTree(convertToString(t)), emptyJsonNode());
    }

    @Override
    public <T> T convertToObject(String s, Class<T> objectClass) {
        return convert(() -> objectMapper.readValue(s, objectClass));
    }

    public JsonNode emptyJsonNode() {
        return convert(() -> objectMapper.readTree("{}"));
    }

    public <R> R convert(ConvertFunction<R> convertFunction, R defaultResult) {
        try {
            return convertFunction.convert();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return defaultResult;
    }

    public <R> R convert(ConvertFunction<R> convertFunction) {
        return convert(convertFunction, null);
    }

    public interface ConvertFunction<R> {
        R convert() throws JsonProcessingException;
    }
}
