package it.aces.vlad_project.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Поста Создание", title = "DTO Поста Создание")
public class ArticleCreateDto {
    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 4, max = 50, message = "Длина наименования поста должна быть от 4 до 50 символов")
    private String name;

    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 10, max = 255, message = "Длина поста должна быть от 10 до 255 символов")
    private String article;
}
