package info.theinside.testservice.repositories;

import info.theinside.testservice.model.Message;
import info.theinside.testservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void messageRepositoryTest() {
        User user = new User();
        user.setId(1L);
        user.setName("nikolay");
        user.setPassword("100");

        Message message = new Message();
        message.setMessage("testing mock");
        message.setUser(user);

        entityManager.persist(message);
        entityManager.flush();

        List<Message> first10MessagesByUser = messageRepository.findFirst10ByUserOrderByIdDesc(user);

        Assertions.assertEquals(first10MessagesByUser.get(0).getMessage(), "testing mock");
    }
}
