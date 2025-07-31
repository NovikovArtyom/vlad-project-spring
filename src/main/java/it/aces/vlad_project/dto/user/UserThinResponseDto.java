package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Пользователя Короткий Ответ", title = "DTO Пользователя Короткий Ответ")
public class UserThinResponseDto {
    private UUID id;
    private String email;
    private Set<UUID> role;
}
