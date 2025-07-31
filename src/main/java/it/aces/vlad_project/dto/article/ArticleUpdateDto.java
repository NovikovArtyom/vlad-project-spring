package it.aces.vlad_project.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Поста Редактирование", title = "DTO Поста Редактирование")
public class ArticleUpdateDto {
    private String name;
    private String article;
}
