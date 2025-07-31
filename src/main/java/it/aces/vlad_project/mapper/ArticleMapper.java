package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.article.ArticleCreateDto;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface ArticleMapper extends BaseMapper<ArticleResponseDto, ArticleEntity> {
    ArticleEntity toEntity(ArticleCreateDto articleCreateDto);

    @Mapping(target = "userId", source = "user.id")
    ArticleResponseDto toDto(ArticleEntity articleEntity);
}
