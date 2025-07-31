package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Фильтр", title = "DTO Пользователя Фильтр")
public class UserFilterDto {
    private String email;
}
