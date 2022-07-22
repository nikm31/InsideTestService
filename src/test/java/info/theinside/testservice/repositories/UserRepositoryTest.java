package info.theinside.testservice.repositories;

import info.theinside.testservice.exceptions.ResourceNotFoundException;
import info.theinside.testservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void messageRepositoryTest() {

        User user = userRepository.findByName("nikolay").orElseThrow(()-> new ResourceNotFoundException("Юзер не найден"));
        Assertions.assertEquals(user.getName(), "nikolay");
    }
}
