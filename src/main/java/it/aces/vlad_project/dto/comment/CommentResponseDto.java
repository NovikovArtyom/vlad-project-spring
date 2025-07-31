package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Ответ", title = "DTO Комментария Ответ")
public class CommentResponseDto {
    private UUID id;
    private UUID userId;
    private String comment;
    private ArticleResponseDto article;
    private VideoResponseDto video;
}
