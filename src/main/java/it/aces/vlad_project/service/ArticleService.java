package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.article.ArticleCreateDto;
import it.aces.vlad_project.dto.article.ArticleFilterDto;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.dto.article.ArticleUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ArticleService {
    Page<ArticleResponseDto> getAllArticles(ArticleFilterDto articleFilterDto, Pageable pageable);

    ArticleResponseDto getArticleById(UUID id);

    ArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, String email);

    ArticleResponseDto updateArticle(UUID id, ArticleUpdateDto articleUpdateDto, String email);

    void deleteArticle(UUID id, String email);
}
