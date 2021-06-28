import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.kuzin19190813.crudwithkafka.controllers.api.ClientApiController;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApiController.class)
@AutoConfigureMockMvc
//@Sql(scripts = {"/schema.sql", "/data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class CheckClientApiControllerWebLayer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOKMessage() throws Exception {
        mockMvc.perform(get("/ping")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("1234")));
    }
}