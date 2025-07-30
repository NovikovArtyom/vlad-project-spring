package it.aces.vlad_project.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Роли Фильтр", title = "DTO Роли Фильтр")
public class RoleFilterDto {
    private String search;
}
