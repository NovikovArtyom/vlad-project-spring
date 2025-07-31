package it.aces.vlad_project.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Видео Ответ", title = "DTO Видео Ответ")
public class VideoResponseDto {
    private UUID id;
    private UUID userId;
    private String name;
    private String url;
}
