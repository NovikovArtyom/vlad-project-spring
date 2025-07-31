package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.AuthApi;
import it.aces.vlad_project.dto.user.LoginDto;
import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.service.AuthService;
import it.aces.vlad_project.service.impl.UserServiceImpl;
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
    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<String> login(LoginDto loginDto) {
        log.debug("Запрос на получение токена: {}", loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(authService.token(loginDto));
    }

    @Override
    public ResponseEntity<UserResponseDto> createUser(UserCreateDto userCreateDto) {
        log.debug("Запрос на создание User: {}", userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }
}
