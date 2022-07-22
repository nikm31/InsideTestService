package info.theinside.testservice.services;

import info.theinside.testservice.model.User;
import info.theinside.testservice.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setName("Roman");
        userFromDB.setPassword("oifh4309f8h324098fh4398gh8h*&TG*#&Gg97rgff98gf983h4f983h487g*&^F&Fu3r");
        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByName("Roman");
        User userJohn = userService.findByUsername("Roman").get();
        Assertions.assertNotNull(userJohn);
        Mockito.verify(userRepository, Mockito.times(1)).findByName(ArgumentMatchers.eq("Roman"));
    }
}
