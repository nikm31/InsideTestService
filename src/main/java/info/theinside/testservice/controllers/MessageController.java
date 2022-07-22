package info.theinside.testservice.controllers;

import info.theinside.testservice.dtos.MessageDto;
import info.theinside.testservice.dtos.MessageRequest;
import info.theinside.testservice.exceptions.DataValidationException;
import info.theinside.testservice.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
@Tag(name = "Сообщения", description = "Методы работы с сообщениями")
public class MessageController {
    private final MessageService messageService;

    @Operation(
            summary = "Запрос последних 10 сообщений пользователя или сохранение сообщения",
            responses = {
                    @ApiResponse(
                            description = "Сообщение сохранено", responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Последние 10 сообщений пользователя получены", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MessageDto.class))
                    ),
                    @ApiResponse(
                            description = "Юзер не найден", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = DataValidationException.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized", responseCode = "401"
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<?> createAuthToken(@RequestBody MessageRequest messageRequest) {
        // проверяем текст сообщения
        if (messageRequest.getMessage().equals("history 10")) {
            List<MessageDto> messages = messageService.getLastTenMessages(messageRequest).stream()
                    .map(MessageDto::new).collect(Collectors.toList());
            return ResponseEntity.ok(messages);
        }
        messageService.saveMessage(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
