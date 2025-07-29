package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Создание", title = "DTO Пользователя Создание")
public class UserCreateDto {
    private String email;
    private String password;
}
