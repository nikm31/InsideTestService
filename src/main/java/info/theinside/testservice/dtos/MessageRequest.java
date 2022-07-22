package info.theinside.testservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "Сообщение от пользователя")
public class MessageRequest {

    @Schema(description = "имя отправителя", example = "nikolay", required = true)
    private String name;

    @Schema(description = "текст сообщения", example = "test message", required = true)
    private String message;

}
