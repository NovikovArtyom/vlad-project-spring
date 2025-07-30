package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.RoleApi;
import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleFilterDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.dto.role.RoleUpdateDto;
import it.aces.vlad_project.service.RoleService;
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
public class RoleController implements RoleApi {
    private final RoleService roleService;

    @Override
    public ResponseEntity<ApiResponse<RoleResponseDto>> getAllRoles(RoleFilterDto roleFilterDto, Pageable pageable) {
        log.debug("Запрос на получение списка Role");
        Page<RoleResponseDto> page = roleService.getAllRoles(roleFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(page));
    }

    @Override
    public ResponseEntity<RoleResponseDto> getRoleById(UUID id) {
        log.debug("Запрос на получение Role по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(id));
    }

    @Override
    public ResponseEntity<RoleResponseDto> createRole(RoleCreateDto roleCreateDto) {
        log.debug("Запрос на создание Role: {}", roleCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.createRole(roleCreateDto));
    }

    @Override
    public ResponseEntity<RoleResponseDto> updateRole(UUID id, RoleUpdateDto roleUpdateDto) {
        log.debug("Запрос на редактирование Role: {}", roleUpdateDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(roleService.updateRole(id, roleUpdateDto));
    }

    @Override
    public ResponseEntity<Void> deleteRole(UUID id) {
        log.debug("Запрос на удаление Role: {}", id);
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
