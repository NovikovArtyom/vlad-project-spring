package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Создание", title = "DTO Комментария Создание")
public class CommentCreateDto {
    @NotBlank
    private String comment;
    private UUID articleId;
    private UUID videoId;
}
