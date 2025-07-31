package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.ArticleApi;
import it.aces.vlad_project.dto.article.ArticleCreateDto;
import it.aces.vlad_project.dto.article.ArticleFilterDto;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.dto.article.ArticleUpdateDto;
import it.aces.vlad_project.service.ArticleService;
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
public class ArticleController implements ArticleApi {
    private final ArticleService articleService;

    @Override
    public ResponseEntity<ApiResponse<ArticleResponseDto>> getAllArticles(ArticleFilterDto articleFilterDto,
                                                                          Pageable pageable) {
        log.debug("Запрос на получение списка Article");
        Page<ArticleResponseDto> page = articleService.getAllArticles(articleFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(page));
    }

    @Override
    public ResponseEntity<ArticleResponseDto> getArticleById(UUID id) {
        log.debug("Запрос на получение Article по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticleById(id));
    }

    @Override
    public ResponseEntity<ArticleResponseDto> createArticle(ArticleCreateDto articleCreateDto,
                                                            Authentication authentication) {
        log.debug("Запрос на создание Article: {}", articleCreateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(articleCreateDto, email));
    }

    @Override
    public ResponseEntity<ArticleResponseDto> updateArticle(UUID id, ArticleUpdateDto articleUpdateDto,
                                                            Authentication authentication) {
        log.debug("Запрос на редактирование Article: {}", articleUpdateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(articleService.updateArticle(id, articleUpdateDto, email));
    }

    @Override
    public ResponseEntity<Void> deleteArticle(UUID id, Authentication authentication) {
        log.debug("Запрос на удаление Article: {}", id);
        String email = authentication.getName();
        articleService.deleteArticle(id, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
