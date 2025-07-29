package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Пользователи")
@RequestMapping(Constants.API_CONTEXT_VERSION + UserApi.API_PATH)
public interface UserApi {
    String API_PATH = "/user";

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id);

    @Operation(summary = "Создание пользователя")
    @PostMapping
    ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto);

    @Operation(summary = "Редактирование пользователя")
    @PatchMapping("/{id}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDto userUpdateDto);

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id);
}
