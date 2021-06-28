package ru.sberbank.kuzin19190813.crudwithkafka.util.converter.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

public interface JsonConverter {
    <T> String convertToString(T t);
    JsonNode convertToJson(String s);
    <T> JsonNode convertToJson(T t);
    <T> T convertToObject(String s, Class<T> objectClass);
}
