package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.comment.CommentCreateDto;
import it.aces.vlad_project.dto.comment.CommentResponseDto;
import it.aces.vlad_project.dto.comment.CommentThinResponseDto;
import it.aces.vlad_project.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING, uses={ArticleMapper.class, VideoMapper.class})
public interface CommentMapper extends BaseMapper<CommentResponseDto, CommentEntity> {
    CommentEntity toEntity(CommentCreateDto commentCreateDto);

    @Mapping(target = "userId", source = "user.id")
    CommentResponseDto toDto(CommentEntity commentEntity);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "articleId", source = "article.id")
    @Mapping(target = "videoId", source = "video.id")
    CommentThinResponseDto toThinDto(CommentEntity commentEntity);
}
