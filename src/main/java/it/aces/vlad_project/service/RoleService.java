package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleFilterDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.dto.role.RoleUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {
    Page<RoleResponseDto> getAllRoles(RoleFilterDto roleFilterDto, Pageable pageable);

    RoleResponseDto getRoleById(UUID id);

    RoleResponseDto createRole(RoleCreateDto roleCreateDto);

    RoleResponseDto updateRole(UUID id, RoleUpdateDto roleUpdateDto);

    void deleteRole(UUID id);
}
