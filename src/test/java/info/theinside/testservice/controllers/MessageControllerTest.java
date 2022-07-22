package info.theinside.testservice.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveMessageTest() throws Exception {
        String jsonMessageRequestToSave = "{\n" +
                "\t\"name\": \"nikolay\",\n" +
                "\t\"message\": \"new message\"\n" +
                "}";

        MvcResult result = mockMvc.perform(post("/api/v1/message/")
                        .header("Authorization", "Bearer_" + getToken())
                        .content(jsonMessageRequestToSave)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertEquals(content, "Сообщение сохранено");
    }

    @Test
    public void getLast10MessagesTest() throws Exception {
        String jsonMessageRequestGetLast10 = "{\n" +
                "\t\"name\": \"nikolay\",\n" +
                "\t\"message\": \"history 10\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/message/")
                        .header("Authorization", "Bearer_" + getToken())
                        .content(jsonMessageRequestGetLast10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private String getToken() throws Exception {
        String jsonAuthRequest = "{\n" +
                "\t\"name\": \"nikolay\",\n" +
                "\t\"password\": \"100\"\n" +
                "}";

        MvcResult result = mockMvc.perform(
                        post("/api/v1/auth")
                                .content(jsonAuthRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        String token = result.getResponse().getContentAsString();
        return token.replace("{\"token\":\"", "").replace("\"}", "");
    }

}
