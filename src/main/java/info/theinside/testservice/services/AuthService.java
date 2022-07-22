package info.theinside.testservice.services;

import info.theinside.testservice.dtos.AuthRequest;
import info.theinside.testservice.dtos.AuthResponse;
import info.theinside.testservice.exceptions.DataValidationException;
import info.theinside.testservice.exceptions.ResourceNotFoundException;
import info.theinside.testservice.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    // получение токена по логину и паролю
    public AuthResponse createAuthToken(AuthRequest authRequest) {
        userService.findByUsername(authRequest.getName()).orElseThrow(
                () -> new ResourceNotFoundException("Юзер не найден: " + authRequest.getName()));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new DataValidationException(List.of("Не верное имя пользователя или пароль"));
        }
        // получаем UserDetails для генерации токена
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getName());
        return new AuthResponse(jwtTokenUtil.generateToken(userDetails));
    }
}
