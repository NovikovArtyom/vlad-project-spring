package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.user.*;
import it.aces.vlad_project.entity.RoleEntity;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.mapper.UserMapper;
import it.aces.vlad_project.repository.RoleRepository;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.repository.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(UUID id) {
        log.debug("Получение User по id = {}", id);
        UserEntity user = userRepository.findEntityById(id, UserEntity.class);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Получение User по email = {}", email);
        UserEntity user = userRepository.findByEmail(email);
        Set<SimpleGrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getTitle()))
                .collect(Collectors.toSet());
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Transactional(readOnly = true)
    public Page<UserThinResponseDto> getAllUsers(UserFilterDto userFilterDto, Pageable pageable) {
        log.debug("Получение списка User");
        return userRepository.findAll(UserSpecification.filter(userFilterDto), pageable)
                .map(userMapper::toUserThinDto);
    }

    @Transactional
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        log.debug("Создание User на основе Dto: {}", userCreateDto);
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Пользователь с email = %s уже зарегистрирован", userCreateDto.getEmail())
            );
        }
        UserEntity user = userMapper.toEntity(userCreateDto);
        Set<RoleEntity> roles = userCreateDto.getRole().stream()
                .map(item -> roleRepository.findEntityById(item, RoleEntity.class))
                .collect(Collectors.toSet());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(roles);
        UserEntity createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    @Transactional
    public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
        log.debug("Редактирование User на основе Dto: {}", userUpdateDto);
        UserEntity user = userRepository.findEntityById(id, UserEntity.class);
        user.setEmail(userUpdateDto.getEmail());
        UserEntity updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Transactional
    public void deleteUser(UUID id) {
        log.debug("Удаление User по id = {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User с id = %s не найден", id));
        }
        userRepository.deleteById(id);
    }
}
