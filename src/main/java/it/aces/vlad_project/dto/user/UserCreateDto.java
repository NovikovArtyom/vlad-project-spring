package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Создание", title = "DTO Пользователя Создание")
public class UserCreateDto {
    private String email;
    private Set<UUID> role;
    private String password;
}
