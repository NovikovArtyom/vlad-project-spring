package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для получение токена", title = "DTO для получение токена")
public class LoginDto {
    private String email;
    private String password;
}
