package it.aces.vlad_project.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Видео Создание", title = "DTO Видео Создание")
public class VideoCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String url;
}
