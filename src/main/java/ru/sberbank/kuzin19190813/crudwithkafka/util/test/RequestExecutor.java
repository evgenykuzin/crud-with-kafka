package ru.sberbank.kuzin19190813.crudwithkafka.util.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;

@Slf4j
public class RequestExecutor {
    protected static final String baseUrl = "http://localhost:8080";
    protected static final Class<RespBody> responseClass = RespBody.class;
    protected final RestTemplate restTemplate;
    protected final String endpoint;

    public RequestExecutor(String baseEndpoint) {
        this.endpoint = baseUrl+baseEndpoint;
        restTemplate = new RestTemplate();
    }

    public RespBody executeGetRequest(String path) {
        return restTemplate.getForObject(endpoint+path, responseClass);
    }

    public RespBody executePostRequest(String path, Object body) {
        return restTemplate.postForObject(endpoint+path, body, responseClass);
    }

}
