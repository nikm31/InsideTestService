package info.theinside.testservice.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityAreaAnonimClient() throws Exception {
        mockMvc.perform(post("/api/v1/message/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void securityTokenTest() throws Exception {
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
        token = token.replace("{\"token\":\"", "").replace("\"}", "");

        String jsonMessageRequest = "{\n" +
                "\t\"name\": \"nikolay\",\n" +
                "\t\"message\": \"some message\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/message/")
                        .header("Authorization", "Bearer_" + token)
                .content(jsonMessageRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
