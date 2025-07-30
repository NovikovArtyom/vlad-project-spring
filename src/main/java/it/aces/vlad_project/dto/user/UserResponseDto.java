package it.aces.vlad_project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import lombok.*;

import java.util.Set;
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
    private Set<RoleResponseDto> role;
}
