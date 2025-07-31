package it.aces.vlad_project.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Поста Создание", title = "DTO Поста Создание")
public class ArticleCreateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String article;
}
