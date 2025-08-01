package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Поле обязательно для заполнения")
    @Email(message = "Укажите валидный email")
    private String email;

    @NotNull(message = "Поле обязательно для заполнения")
    @NotEmpty(message = "Необходимо указать хотя бы одну роль для пользователя")
    private Set<UUID> role;

    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 8, max = 25, message = "Длинна пароля должна быть от 8 до 25 символов")
    private String password;
}
