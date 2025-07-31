package it.aces.vlad_project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.aces.vlad_project.config.Constants;
import it.aces.vlad_project.dto.article.ArticleCreateDto;
import it.aces.vlad_project.dto.article.ArticleFilterDto;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.dto.article.ArticleUpdateDto;
import it.aces.vlad_project.util.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Посты")
@RequestMapping(Constants.API_CONTEXT_VERSION + ArticleApi.API_PATH)
public interface ArticleApi {
    String API_PATH = "/article";

    @Operation(summary = "Получение списка постов")
    @GetMapping
    ResponseEntity<ApiResponse<ArticleResponseDto>> getAllArticles(@ParameterObject ArticleFilterDto articleFilterDto,
                                                                   @ParameterObject Pageable pageable);

    @Operation(summary = "Получение поста по id")
    @GetMapping("/{id}")
    ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable UUID id);

    @Operation(summary = "Создание поста")
    @PostMapping
    ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleCreateDto articleCreateDto,
                                                     Authentication authentication);

    @Operation(summary = "Редактирование поста")
    @PatchMapping("/{id}")
    ResponseEntity<ArticleResponseDto> updateArticle(@PathVariable UUID id, @RequestBody ArticleUpdateDto articleUpdateDto,
                                                     Authentication authentication);

    @Operation(summary = "Удаление поста")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteArticle(@PathVariable UUID id, Authentication authentication);
}
