package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.video.VideoCreateDto;
import it.aces.vlad_project.dto.video.VideoFilterDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import it.aces.vlad_project.dto.video.VideoUpdateDto;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.entity.VideoEntity;
import it.aces.vlad_project.mapper.VideoMapper;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.repository.VideoRepository;
import it.aces.vlad_project.repository.specification.VideoSpecification;
import it.aces.vlad_project.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public Page<VideoResponseDto> getAllVideos(VideoFilterDto videoFilterDto, Pageable pageable) {
        log.debug("Получение списка Video");
        return videoRepository.findAll(VideoSpecification.filter(videoFilterDto), pageable).map(videoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public VideoResponseDto getVideoById(UUID id) {
        log.debug("Получение Video по id = {}", id);
        VideoEntity video = videoRepository.findEntityById(id, VideoEntity.class);
        return videoMapper.toDto(video);
    }

    @Override
    @Transactional
    public VideoResponseDto createVideo(VideoCreateDto videoCreateDto, String email) {
        log.debug("Создание Video на основе Dto: {}", videoCreateDto);
        VideoEntity video = videoMapper.toEntity(videoCreateDto);
        UserEntity user = userRepository.findByEmail(email);
        video.setUser(user);
        VideoEntity createdVideo = videoRepository.save(video);
        emailService.sendEmail(1, email, VideoEntity.class.getSimpleName());
        return videoMapper.toDto(createdVideo);
    }

    @Override
    @Transactional
    public VideoResponseDto updateVideo(UUID id, VideoUpdateDto videoUpdateDto, String email) {
        log.debug("Редактирование Video на основе Dto: {}", videoUpdateDto);
        VideoEntity video = videoRepository.findEntityById(id, VideoEntity.class);
        permissionService.checkOwnerOrAdmin(video, email, VideoEntity::getUser);
        if (videoUpdateDto.getName() != null && !videoUpdateDto.getName().isEmpty()) {
            video.setName(videoUpdateDto.getName());
        }
        if (videoUpdateDto.getUrl() != null && !videoUpdateDto.getUrl().isEmpty()) {
            video.setUrl(videoUpdateDto.getUrl());
        }
        VideoEntity updatedVideo = videoRepository.save(video);
        emailService.sendEmail(2, email, VideoEntity.class.getSimpleName());
        return videoMapper.toDto(updatedVideo);
    }

    @Override
    @Transactional
    public void deleteVideo(UUID id, String email) {
        log.debug("Удаление Video по id = {}", id);
        VideoEntity video = videoRepository.findEntityById(id, VideoEntity.class);
        permissionService.checkOwnerOrAdmin(video, email, VideoEntity::getUser);
        videoRepository.delete(video);
        emailService.sendEmail(3, email, VideoEntity.class.getSimpleName());
    }
}
