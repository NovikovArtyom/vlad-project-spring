package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Короткий Ответ", title = "DTO Комментария Короткий Ответ")
public class CommentThinResponseDto {
    private UUID id;
    private UUID userId;
    private String comment;
    private UUID articleId;
    private UUID videoId;
}
