package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.AuthApi;
import it.aces.vlad_project.dto.user.LoginDto;
import it.aces.vlad_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public ResponseEntity<String> login(LoginDto loginDto) {
        log.debug("Запрос на получение токена: {}", loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(authService.token(loginDto));
    }
}
