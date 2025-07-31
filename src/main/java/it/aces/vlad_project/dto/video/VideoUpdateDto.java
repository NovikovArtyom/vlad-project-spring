package it.aces.vlad_project.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Видео Редактирование", title = "DTO Видео Редактирование")
public class VideoUpdateDto {
    private String name;
    private String url;
}
