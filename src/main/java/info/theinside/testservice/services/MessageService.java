package info.theinside.testservice.services;

import info.theinside.testservice.dtos.MessageRequest;
import info.theinside.testservice.exceptions.ResourceNotFoundException;
import info.theinside.testservice.model.Message;
import info.theinside.testservice.model.User;
import info.theinside.testservice.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<Message> getLastTenMessages(MessageRequest messageRequest) {
        User user = getUser(messageRequest.getName());
        return messageRepository.findFirst10ByUserOrderByIdDesc(user);
    }

    public void saveMessage(MessageRequest messageRequest) {
        User user = getUser(messageRequest.getName());

        Message message = new Message();
        message.setMessage(messageRequest.getMessage());
        message.setUser(user);

        messageRepository.save(message);
    }

    private User getUser(String userName) {
        return userService.findByUsername(userName).orElseThrow(() -> new ResourceNotFoundException("Юзер не найден"));
    }
}
