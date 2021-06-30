package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.NotFoundBody;
import ru.sberbank.kuzin19190813.crudwithkafka.body.response.RespBody;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.util.CrudRequestExecutor;
import ru.sberbank.kuzin19190813.crudwithkafka.util.test.TestMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CrudControllerTest<D> extends BaseControllerTest {
    protected String baseEndpoint;
    protected String entityName;
    protected Class<D> dtoClass;
    protected CrudRequestExecutor crudRequestExecutor;

    public CrudControllerTest(String baseEndpoint, String entityName, Class<D> dtoClass) {
        this.baseEndpoint = baseEndpoint;
        this.entityName = entityName;
        this.dtoClass = dtoClass;
        crudRequestExecutor = CrudRequestExecutor.getInstance(baseEndpoint);
        crudRequestExecutor.clearAll();
    }

    public abstract D constructTestObject();

    public abstract ModifiedResult<D> modifyTestObject(D d);

    public abstract void testsForGet();

    public abstract void testsForSave();

    public abstract void testsForUpdate();

    public abstract void testsForDelete();

    public abstract void testsForFindAll();

    @Test
    protected void get() {
        D constructed = constructTestObject();
        Long id = saveObject(constructed);
        D d = getObject(id);
        assertEquals(constructed.toString(), d.toString());
        Long illegalId = 123456L;
        assertNotFound(illegalId);
        testsForGet();
        crudRequestExecutor.clearAll();
    }

    @Test
    protected void save() {
        D obj = constructTestObject();
        Long id = saveObject(obj);
        assertNotNull(id);
        assertEquals(obj.toString(), getObject(id).toString());
        testsForSave();
        crudRequestExecutor.clearAll();
    }

    @Test
    protected void update() {
        Long savedId = saveObject(constructTestObject());
        D d = getObject(savedId);
        ModifiedResult<D> modifiedResult = modifyTestObject(d);
        String mfn = modifiedResult.getModifiedFieldName();
        Object mfv = modifiedResult.getModifiedFieldValue();
        Object oldValue = modifiedResult.getOldFieldValue();
        crudRequestExecutor.update(d);
        D d2 = getObject(savedId);
        Object d2Value = getFieldValue(d2, mfn);
        assertEquals(mfv, d2Value);
        assertNotEquals(mfv, oldValue);
        testsForUpdate();
        crudRequestExecutor.clearAll();
    }

    @Test
    protected void delete() {
        Long id = saveObject(constructTestObject());
        crudRequestExecutor.delete(id);
        assertNotFound(id);
        testsForDelete();
        crudRequestExecutor.clearAll();
    }

    @Test
    protected void findAll() {
        List<D> createdItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            createdItems.add(constructTestObject());
        }
        createdItems.forEach(crudRequestExecutor::save);

        RespBody respBody = crudRequestExecutor.findAll();
        System.out.println("respBody = " + respBody);
        List<D> foundedItems = TestMapper.mapToDtoList(respBody.getResult(), dtoClass);
        assertEquals(createdItems.toString(), foundedItems.toString());
        testsForFindAll();
        crudRequestExecutor.clearAll();
    }

    private Object getFieldValue(D object, String fieldName) {
        Object fieldValue = null;
        try {
            Field field = dtoClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            fieldValue = field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            fail(e);
        }
        return fieldValue;
    }

    protected D getObject(Long id) {
        return TestMapper.map(crudRequestExecutor.get(id).getResult(), dtoClass);
    }

    protected Long saveObject(D d) {
        return TestMapper.mapToLong(crudRequestExecutor.save(d).getResult());
    }

    protected void assertNotFound(Long id) {
        RespBody errorResp = crudRequestExecutor.get(id, false);
        assertNull(errorResp.getResult());
        NotFoundBody notFoundBody = TestMapper.map(errorResp.getError(), NotFoundBody.class);
        assertEquals(new NotFoundBody(entityName, id), notFoundBody);
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @AllArgsConstructor
    public static class ModifiedResult<D> {
        D obj;
        String modifiedFieldName;
        Object modifiedFieldValue;
        Object oldFieldValue;
    }
}
