package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserUpdateDto;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.mapper.UserMapper;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.service.UserService;
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
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(UUID id) {
//        log.debug("Получение User по id = {}", id);
        UserEntity user = userRepository.findEntityById(id, UserEntity.class);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
//        log.debug("Создание User на основе Dto: {}", userCreateDto);
        UserEntity user = userMapper.toEntity(userCreateDto);
        UserEntity createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
//        log.debug("Редактирование User на основе Dto: {}", userUpdateDto);
        UserEntity user = userRepository.findEntityById(id, UserEntity.class);
        user.setEmail(userUpdateDto.getEmail());
        UserEntity updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
//        log.debug("Удаление User по id = {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User с id = %s не найден", id));
        }
        userRepository.deleteById(id);
    }
}
