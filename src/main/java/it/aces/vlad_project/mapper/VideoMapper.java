package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.video.VideoCreateDto;
import it.aces.vlad_project.dto.video.VideoResponseDto;
import it.aces.vlad_project.entity.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface VideoMapper extends BaseMapper<VideoResponseDto, VideoEntity> {
    VideoEntity toEntity(VideoCreateDto videoCreateDto);

    @Mapping(target = "userId", source = "user.id")
    VideoResponseDto toDto(VideoEntity videoEntity);
}
