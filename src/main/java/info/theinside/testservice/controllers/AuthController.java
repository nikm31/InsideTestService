package info.theinside.testservice.controllers;

import info.theinside.testservice.dtos.AuthRequest;
import info.theinside.testservice.exceptions.DataValidationException;
import info.theinside.testservice.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Авторизация", description = "Методы работы с авторизацией пользователей")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Запрос авторизацию и получение токена",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Юзер не найден", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = DataValidationException.class))
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.createAuthToken(authRequest));
    }
}
