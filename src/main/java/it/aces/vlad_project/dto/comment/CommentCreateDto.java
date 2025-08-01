package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Создание", title = "DTO Комментария Создание")
public class CommentCreateDto {
    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 10, max = 255, message = "Длина комментария должна быть от 10 до 255 символов")
    private String comment;

    private UUID articleId;
    private UUID videoId;
}
