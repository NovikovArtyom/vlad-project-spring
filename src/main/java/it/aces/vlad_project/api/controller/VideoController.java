package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.VideoApi;
import it.aces.vlad_project.dto.video.VideoCreateDto;
import it.aces.vlad_project.dto.video.VideoFilterDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import it.aces.vlad_project.dto.video.VideoUpdateDto;
import it.aces.vlad_project.service.VideoService;
import it.aces.vlad_project.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoController implements VideoApi {
    private final VideoService videoService;

    @Override
    public ResponseEntity<ApiResponse<VideoResponseDto>> getAllVideos(VideoFilterDto videoFilterDto, Pageable pageable) {
        log.debug("Запрос на получение списка Video");
        Page<VideoResponseDto> page = videoService.getAllVideos(videoFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(page));
    }

    @Override
    public ResponseEntity<VideoResponseDto> getVideoById(UUID id) {
        log.debug("Запрос на получение Video по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideoById(id));
    }

    @Override
    public ResponseEntity<VideoResponseDto> createVideo(VideoCreateDto videoCreateDto, Authentication authentication) {
        log.debug("Запрос на создание Video: {}", videoCreateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.createVideo(videoCreateDto, email));
    }

    @Override
    public ResponseEntity<VideoResponseDto> updateVideo(UUID id, VideoUpdateDto videoUpdateDto,
                                                        Authentication authentication) {
        log.debug("Запрос на редактирование Video: {}", videoUpdateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(videoService.updateVideo(id, videoUpdateDto, email));
    }

    @Override
    public ResponseEntity<Void> deleteVideo(UUID id, Authentication authentication) {
        log.debug("Запрос на удаление Video: {}", id);
        String email = authentication.getName();
        videoService.deleteVideo(id, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
