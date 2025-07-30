package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.dto.role.RoleUpdateDto;
import it.aces.vlad_project.entity.RoleEntity;
import it.aces.vlad_project.mapper.RoleMapper;
import it.aces.vlad_project.repository.RoleRepository;
import it.aces.vlad_project.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto getRoleById(UUID id) {
        log.debug("Получение Role по id = {}", id);
        RoleEntity role = roleRepository.findEntityById(id, RoleEntity.class);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional
    public RoleResponseDto createRole(RoleCreateDto roleCreateDto) {
        log.debug("Создание Role на основе Dto: {}", roleCreateDto);
        RoleEntity role = roleMapper.toEntity(roleCreateDto);
        RoleEntity createdRole = roleRepository.save(role);
        return roleMapper.toDto(createdRole);
    }

    @Override
    @Transactional
    public RoleResponseDto updateRole(UUID id, RoleUpdateDto roleUpdateDto) {
        log.debug("Редактирование Role на основе Dto: {}", roleUpdateDto);
        RoleEntity role = roleRepository.findEntityById(id, RoleEntity.class);
        if (roleUpdateDto.getTitle() != null && !roleUpdateDto.getTitle().isEmpty()) {
            role.setTitle(roleUpdateDto.getTitle());
        }
        if (roleUpdateDto.getDescription() != null && !roleUpdateDto.getDescription().isEmpty()) {
            role.setDescription(roleUpdateDto.getDescription());
        }
        RoleEntity updatedRole = roleRepository.save(role);
        return roleMapper.toDto(updatedRole);
    }

    @Override
    @Transactional
    public void deleteRole(UUID id) {
        log.debug("Удаление Role по id = {}", id);
        if (!roleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role с id = %s не найден", id));
        }
        roleRepository.deleteById(id);
    }
}
