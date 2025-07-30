package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.user.LoginDto;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.service.AuthService;
import it.aces.vlad_project.util.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public String token(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Логин или пароль не действительны"
            );
        }
        Optional<UserEntity> userOptional = Optional.of(userRepository.findByEmail(loginDto.getEmail()));
        Map<String, String> tokensStorage = jwtToken.generateToken(userOptional.get());
        return tokensStorage.get("accessToken");
    }
}
