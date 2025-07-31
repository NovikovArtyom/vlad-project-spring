package it.aces.vlad_project.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Поста Фильтр", title = "DTO Поста Фильтр")
public class ArticleFilterDto {
    private String search;
    private String name;
}
