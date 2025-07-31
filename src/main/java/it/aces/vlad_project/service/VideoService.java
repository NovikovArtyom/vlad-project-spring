package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.video.VideoCreateDto;
import it.aces.vlad_project.dto.video.VideoFilterDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import it.aces.vlad_project.dto.video.VideoUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VideoService {
    Page<VideoResponseDto> getAllVideos(VideoFilterDto videoFilterDto, Pageable pageable);

    VideoResponseDto getVideoById(UUID id);

    VideoResponseDto createVideo(VideoCreateDto videoCreateDto, String email);

    VideoResponseDto updateVideo(UUID id, VideoUpdateDto videoUpdateDto, String email);

    void deleteVideo(UUID id, String email);
}
