package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.UserApi;
import it.aces.vlad_project.dto.user.*;
import it.aces.vlad_project.service.impl.UserServiceImpl;
import it.aces.vlad_project.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<ApiResponse<UserThinResponseDto>> getAllUsers(UserFilterDto userFilterDto, Pageable pageable) {
        log.debug("Запрос на получение списка User");
        Page<UserThinResponseDto> page = userService.getAllUsers(userFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(page));
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(UUID id) {
        log.debug("Запрос на получение User по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
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
