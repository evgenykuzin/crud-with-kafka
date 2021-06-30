package ru.sberbank.kuzin19190813.crudwithkafka.controllers.util;

import lombok.extern.slf4j.Slf4j;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;

@Slf4j
public class CrudRequestExecutor extends BaseControllerTestRequestExecutor {

    private CrudRequestExecutor(String baseEndpoint) {
        super(baseEndpoint);
    }

    public static CrudRequestExecutor getInstance(String baseEndpoint) {
        return new CrudRequestExecutor(baseEndpoint);
    }

    public RespBody clearAll() {
        return executeGetRequest("/clear-all");
    }

    public RespBody get(Long id, boolean failOnErrors) {
        RespBody result = executeGetRequest("/get/" + id);
        return test(() -> result, failOnErrors);
    }

    public RespBody save(Object d, boolean failOnErrors) {
        RespBody result = executePostRequest("/save/", d);
        return test(() -> result, failOnErrors);
    }

    public RespBody update(Object d, boolean failOnErrors) {
        RespBody result = executePostRequest("/update/", d);
        return test(() -> result, failOnErrors);
    }

    public RespBody delete(Long id, boolean failOnErrors) {
        RespBody result = executeGetRequest("/delete/" + id);
        return test(() -> result, failOnErrors);
    }

    public RespBody findAll(boolean failOnErrors) {
        RespBody result = executeGetRequest("/");
        return test(() -> result, failOnErrors);
    }

    public RespBody get(Long id) {
        return get(id, true);
    }

    public RespBody save(Object d) {
        return save(d, true);
    }

    public RespBody update(Object d) {
        return update(d, true);
    }

    public RespBody delete(Long id) {
        return delete(id, true);
    }

    public RespBody findAll() {
        return findAll(true);
    }
}