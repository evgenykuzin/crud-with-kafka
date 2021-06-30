package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.junit.jupiter.api.BeforeAll;
import ru.sberbank.kuzin19190813.crudwithkafka.App;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseControllerTest {
    static AtomicBoolean appStarted = new AtomicBoolean(false);

    @BeforeAll
    public static void initApp() throws Exception {
        if (!appStarted.get()) {
            App.main(new String[0]);
            appStarted.set(true);
        }
    }

}
