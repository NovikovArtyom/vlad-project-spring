package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.video.VideoCreateDto;
import it.aces.vlad_project.dto.video.VideoFilterDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import it.aces.vlad_project.dto.video.VideoUpdateDto;
import it.aces.vlad_project.util.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Видео")
@RequestMapping(Constants.API_CONTEXT_VERSION + VideoApi.API_PATH)
public interface VideoApi {
    String API_PATH = "/video";

    @Operation(summary = "Получение списка видео")
    @GetMapping
    ResponseEntity<ApiResponse<VideoResponseDto>> getAllVideos(@ParameterObject VideoFilterDto videoFilterDto,
                                                               @ParameterObject Pageable pageable);

    @Operation(summary = "Получение видео по id")
    @GetMapping("/{id}")
    ResponseEntity<VideoResponseDto> getVideoById(@PathVariable UUID id);

    @Operation(summary = "Создание видео")
    @PostMapping
    ResponseEntity<VideoResponseDto> createVideo(@Valid @RequestBody VideoCreateDto videoCreateDto,
                                                 Authentication authentication);

    @Operation(summary = "Редактирование видео")
    @PatchMapping("/{id}")
    ResponseEntity<VideoResponseDto> updateVideo(@PathVariable UUID id, @RequestBody VideoUpdateDto videoUpdateDto,
                                                 Authentication authentication);

    @Operation(summary = "Удаление видео")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteVideo(@PathVariable UUID id, Authentication authentication);
}
