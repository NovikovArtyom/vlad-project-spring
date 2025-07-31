package it.aces.vlad_project.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Видео Фильтр", title = "DTO Видео Фильтр")
public class VideoFilterDto {
    private String search;
    private UUID userId;
}
