package it.aces.vlad_project.util;

import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.repository.RoleRepository;
import it.aces.vlad_project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userService.countUsers() == 0) {
            Set<UUID> roles = Set.of(roleRepository.findByTitle("ADMIN").getId());
            UserCreateDto user = new UserCreateDto("artyom.nov.1997@gmail.com", roles, "Nora.11223344");
            userService.createUser(user);
        }
    }
}
