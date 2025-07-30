package it.aces.vlad_project.dto.user;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserThinResponseDto {
    private UUID id;
    private String email;
    private Set<UUID> role;
}
