package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.UserApi;
import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserUpdateDto;
import it.aces.vlad_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDto> getUserById(UUID id) {
        log.debug("Запрос на получение User по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<UserResponseDto> createUser(UserCreateDto userCreateDto) {
        log.debug("Запрос на создание User: {}", userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(UUID id, UserUpdateDto userUpdateDto) {
        log.debug("Запрос на редактирование User: {}", userUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userUpdateDto));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        log.debug("Запрос на удаление User: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
