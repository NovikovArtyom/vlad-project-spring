package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.user.LoginDto;
import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Авторизация")
@RequestMapping(Constants.API_CONTEXT_VERSION + AuthApi.API_PATH)
public interface AuthApi {
    String API_PATH = "/auth";

    @Operation(summary = "Получение access-токена")
    @PostMapping("/token")
    ResponseEntity<String> login(@RequestBody LoginDto loginDto);

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/registration")
    ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto);
}
