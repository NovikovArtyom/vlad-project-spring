package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Редактирование", title = "DTO Пользователя Редактирование")
public class UserUpdateDto {
    @Email
    @NotBlank
    private String email;
}
