package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.entity.RoleEntity;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.repository.RoleRepository;
import it.aces.vlad_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private  <T> boolean isOwnerOrAdmin(T entity, String email, Function<T, UserEntity> ownerExtractor) {
        UserEntity currentUser = userRepository.findByEmail(email);

        RoleEntity adminRole = roleRepository.findByTitle("ADMIN");
        boolean isAdmin = currentUser.getRole().contains(adminRole);

        UserEntity owner = ownerExtractor.apply(entity);
        boolean isOwner = owner != null && owner.equals(currentUser);

        return isOwner || isAdmin;
    }

    public <T> void checkOwnerOrAdmin(
            T entity,
            String email,
            Function<T, UserEntity> ownerExtractor
    ) {
        if (!isOwnerOrAdmin(entity, email, ownerExtractor)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Действие разрешено только администратору или владельцу"
            );
        }
    }
}
