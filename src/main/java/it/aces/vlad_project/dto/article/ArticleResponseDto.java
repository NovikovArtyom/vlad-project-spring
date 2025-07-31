package it.aces.vlad_project.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Поста Ответ", title = "DTO Поста Ответ")
public class ArticleResponseDto {
    private UUID id;
    private UUID userId;
    private String name;
    private String article;
}
