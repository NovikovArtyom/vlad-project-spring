package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.article.ArticleCreateDto;
import it.aces.vlad_project.dto.article.ArticleFilterDto;
import it.aces.vlad_project.dto.article.ArticleResponseDto;
import it.aces.vlad_project.dto.article.ArticleUpdateDto;
import it.aces.vlad_project.entity.ArticleEntity;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.mapper.ArticleMapper;
import it.aces.vlad_project.repository.ArticleRepository;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.repository.specification.ArticleSpecification;
import it.aces.vlad_project.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    @Override
    public Page<ArticleResponseDto> getAllArticles(ArticleFilterDto articleFilterDto, Pageable pageable) {
        log.debug("Получение списка Article");
        return articleRepository.findAll(ArticleSpecification.filter(articleFilterDto), pageable)
                .map(articleMapper::toDto);
    }

    @Override
    public ArticleResponseDto getArticleById(UUID id) {
        log.debug("Получение Article по id = {}", id);
        ArticleEntity article = articleRepository.findEntityById(id, ArticleEntity.class);
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, String email) {
        log.debug("Создание Article на основе Dto: {}", articleCreateDto);
        ArticleEntity article = articleMapper.toEntity(articleCreateDto);
        UserEntity user = userRepository.findByEmail(email);
        article.setUser(user);
        ArticleEntity createdArticle = articleRepository.save(article);
        return articleMapper.toDto(createdArticle);
    }

    @Override
    public ArticleResponseDto updateArticle(UUID id, ArticleUpdateDto articleUpdateDto, String email) {
        log.debug("Редактирование Article на основе Dto: {}", articleUpdateDto);
        ArticleEntity article = articleRepository.findEntityById(id, ArticleEntity.class);
        permissionService.checkOwnerOrAdmin(article, email, ArticleEntity::getUser);
        if (articleUpdateDto.getName() != null && !articleUpdateDto.getName().isEmpty()) {
            article.setName(articleUpdateDto.getName());
        }
        if (articleUpdateDto.getArticle() != null && !articleUpdateDto.getArticle().isEmpty()) {
            article.setArticle(articleUpdateDto.getArticle());
        }
        ArticleEntity updatedArticle = articleRepository.save(article);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    public void deleteArticle(UUID id, String email) {
        log.debug("Удаление Article по id = {}", id);
        ArticleEntity article = articleRepository.findEntityById(id, ArticleEntity.class);
        permissionService.checkOwnerOrAdmin(article, email, ArticleEntity::getUser);
        articleRepository.deleteById(id);
    }
}
