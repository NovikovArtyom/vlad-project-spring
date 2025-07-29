package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Ответ", title = "DTO Пользователя Ответ")
public class UserResponseDto {
    private UUID id;
    private String email;
}
