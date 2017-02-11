package edgeapp;


import egdeapp.EdgeServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testing
 * Testing Scenario:
 * 1. Request home page
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EdgeServiceApplication.class)
@AutoConfigureMockMvc
public class WebControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void indexTest() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:index.html"));
    }
}
