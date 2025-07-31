package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Фильтр", title = "DTO Комментария Фильтр")
public class CommentFilterDto {
    private String search;
    private UUID articleId;
    private UUID videoId;
}
