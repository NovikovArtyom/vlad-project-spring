package it.aces.vlad_project.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Комментария Редактирование", title = "DTO Комментария Редактирование")
public class CommentUpdateDto {
    private String comment;
}
