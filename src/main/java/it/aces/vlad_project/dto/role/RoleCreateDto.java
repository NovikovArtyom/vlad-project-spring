package it.aces.vlad_project.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Роли Создание", title = "DTO Роли Создание")
public class RoleCreateDto {
    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 5, max = 50, message = "Длина комментария должна быть от 5 до 50 символов")
    private String title;

    private String description;
}
