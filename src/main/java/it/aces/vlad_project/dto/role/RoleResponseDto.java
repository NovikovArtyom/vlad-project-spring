package it.aces.vlad_project.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Роли Ответ", title = "DTO Роли Ответ")
public class RoleResponseDto {
    private UUID id;
    private String title;
    private String description;
}
