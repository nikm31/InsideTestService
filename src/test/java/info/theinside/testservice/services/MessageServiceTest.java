package info.theinside.testservice.services;

import info.theinside.testservice.dtos.MessageDto;
import info.theinside.testservice.dtos.MessageRequest;
import info.theinside.testservice.model.Message;
import info.theinside.testservice.model.User;
import info.theinside.testservice.repositories.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserService userService;

    @Test
    public void findLast10UserMessagesTest() {

        User user = new User();
        user.setName("nikolay");
        user.setPassword("oifh4309f8h324098fh4398gh8h*&TG*#&Gg97rgff98gf983h4f983h487g*&^F&Fu3r");

        Optional<User> use = Optional.of(user);

        Mockito.doReturn(use)
                .when(userService)
                .findByUsername("nikolay");

        List<MessageDto> messageDtoList = List.of(new MessageDto("1"), new MessageDto("2"), new MessageDto("3"), new MessageDto("4"), new MessageDto("5"), new MessageDto("6"), new MessageDto("7"), new MessageDto("8"), new MessageDto("9"), new MessageDto("10"));

        Mockito.doReturn(messageDtoList)
                .when(messageRepository)
                .findFirst10ByUserOrderByIdDesc(user);

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage("history 10");
        messageRequest.setName("nikolay");

        List<Message> lastTenMessages = messageService.getLastTenMessages(messageRequest);


        Assertions.assertNotNull(lastTenMessages);

        Mockito.verify(messageRepository, Mockito.times(1)).findFirst10ByUserOrderByIdDesc(user);
    }
}
