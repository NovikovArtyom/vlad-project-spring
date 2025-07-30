package it.aces.vlad_project.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Роли Редактирование", title = "DTO Роли Редактирование")
public class RoleUpdateDto {
    private String title;
    private String description;
}
