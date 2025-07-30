package it.aces.vlad_project.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Роли Создание", title = "DTO Роли Создание")
public class RoleCreateDto {
    @NotBlank
    private String title;
    private String description;
}
