package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleFilterDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.dto.role.RoleUpdateDto;
import it.aces.vlad_project.util.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Роли")
@RequestMapping(Constants.API_CONTEXT_VERSION + RoleApi.API_PATH)
public interface RoleApi {
    String API_PATH = "/role";

    @Operation(summary = "Получение списка ролей")
    @GetMapping
    ResponseEntity<ApiResponse<RoleResponseDto>> getAllRoles(@ParameterObject RoleFilterDto roleFilterDto,
                                                             @ParameterObject Pageable pageable);

    @Operation(summary = "Получение роли по id")
    @GetMapping("/{id}")
    ResponseEntity<RoleResponseDto> getRoleById(@PathVariable UUID id);

    @Operation(summary = "Создание роли")
    @PostMapping
    ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleCreateDto roleCreateDto);

    @Operation(summary = "Редактирование роли")
    @PatchMapping("/{id}")
    ResponseEntity<RoleResponseDto> updateRole(@PathVariable UUID id, @RequestBody RoleUpdateDto roleUpdateDto);

    @Operation(summary = "Удаление роли")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRole(@PathVariable UUID id);
}
