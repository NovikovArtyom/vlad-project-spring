package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.comment.*;
import it.aces.vlad_project.util.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Комментарий")
@RequestMapping(Constants.API_CONTEXT_VERSION + CommentApi.API_PATH)
public interface CommentApi {
    String API_PATH = "/comment";

    @Operation(summary = "Получение списка комментариев")
    @GetMapping
    ResponseEntity<ApiResponse<CommentThinResponseDto>> getAllComments(@ParameterObject CommentFilterDto commentFilterDto,
                                                                       @ParameterObject Pageable pageable);

    @Operation(summary = "Получение комментария по id")
    @GetMapping("/{id}")
    ResponseEntity<CommentResponseDto> getCommentById(@PathVariable UUID id);

    @Operation(summary = "Создание комментария")
    @PostMapping
    ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentCreateDto commentCreateDto,
                                                     Authentication authentication);

    @Operation(summary = "Редактирование комментария")
    @PatchMapping("/{id}")
    ResponseEntity<CommentResponseDto> updateComment(@PathVariable UUID id, @RequestBody CommentUpdateDto commentUpdateDto,
                                                     Authentication authentication);

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteComment(@PathVariable UUID id, Authentication authentication);
}
