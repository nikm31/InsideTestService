package info.theinside.testservice.dtos;

import info.theinside.testservice.model.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "Сообщение")
public class MessageDto {

    @Schema(description = "тело сообщения", example = "message", required = true)
    private String message;

    public MessageDto(Message message) {
        this.message = message.getMessage();
    }

    public MessageDto(String message) {
        this.message = message;
    }
}
