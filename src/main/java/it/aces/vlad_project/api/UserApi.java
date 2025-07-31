package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.user.UserFilterDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserThinResponseDto;
import it.aces.vlad_project.dto.user.UserUpdateDto;
import it.aces.vlad_project.util.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Пользователи")
@RequestMapping(Constants.API_CONTEXT_VERSION + UserApi.API_PATH)
public interface UserApi {
    String API_PATH = "/user";

    @Operation(summary = "Получение списка пользователей")
    @GetMapping("/getAll")
    ResponseEntity<ApiResponse<UserThinResponseDto>> getAllUsers(@ParameterObject UserFilterDto userFilterDto,
                                                                 @ParameterObject Pageable pageable);

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id);

    @Operation(summary = "Редактирование пользователя")
    @PatchMapping("/{id}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDto userUpdateDto);

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id);
}
