package it.aces.vlad_project.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Видео Создание", title = "DTO Видео Создание")
public class VideoCreateDto {
    @NotNull(message = "Поле обязательно для заполнения")
    @Size(min = 5, max = 50, message = "Длина наименования видео должна быть от 5 до 50 символов")
    private String name;

    @NotBlank(message = "Поле обязательно для заполнения")
    private String url;
}
