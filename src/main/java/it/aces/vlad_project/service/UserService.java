package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserUpdateDto;

import java.util.UUID;

public interface UserService {
    UserResponseDto getUserById(UUID id);

    UserResponseDto createUser(UserCreateDto userCreateDto);

    UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto);

    void deleteUser(UUID id);
}
