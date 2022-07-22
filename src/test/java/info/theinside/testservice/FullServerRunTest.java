package info.theinside.testservice;

import info.theinside.testservice.dtos.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {

        AuthResponse authResponse = restTemplate.postForObject("/api/v1/auth", "{\"name\": \"nikolay\", \"password\": \"100\"}", AuthResponse.class);
        assertThat(authResponse).isNotNull();
    }
}