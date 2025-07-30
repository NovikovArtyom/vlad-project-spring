package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.dto.role.RoleUpdateDto;

import java.util.UUID;

public interface RoleService {
    RoleResponseDto getRoleById(UUID id);

    RoleResponseDto createRole(RoleCreateDto roleCreateDto);

    RoleResponseDto updateRole(UUID id, RoleUpdateDto roleUpdateDto);

    void deleteRole(UUID id);
}
