package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.user.LoginDto;

public interface AuthService {
    String token(LoginDto loginDto);
}
