package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CrudController.class)
//@AutoConfigureMockMvc
public abstract class CrudControllerTest<D> extends BaseControllerTest {
    protected RestTemplate restTemplate = new RestTemplate();
    private String endpoint;


    public CrudControllerTest(String baseEndpoint) {
        this.endpoint = baseUrl+baseEndpoint;
    }

    Object executeGetRequest(String path) {
        return restTemplate.getForObject(endpoint+path, Object.class);
    }

    Object executePostRequest(String path, D d) {
        return restTemplate.postForObject(endpoint+path, d, Object.class);
    }

    void get(Long id) {
        Object result = executeGetRequest("/get/" + id);
        System.out.println("result = " + result);
        assertNotNull(result);
    }

    void save(D d) {
        Object result = executePostRequest("/save/", d);
        System.out.println("result = " + result);
        assertNotNull(result);
    }

    void update(D d) {
    }

    void delete(Long id) {
    }

    void getAll() {
    }

}