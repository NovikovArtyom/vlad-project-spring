package it.aces.vlad_project.service.impl;

import it.aces.vlad_project.dto.comment.*;
import it.aces.vlad_project.entity.ArticleEntity;
import it.aces.vlad_project.entity.CommentEntity;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.entity.VideoEntity;
import it.aces.vlad_project.mapper.CommentMapper;
import it.aces.vlad_project.repository.ArticleRepository;
import it.aces.vlad_project.repository.CommentRepository;
import it.aces.vlad_project.repository.UserRepository;
import it.aces.vlad_project.repository.VideoRepository;
import it.aces.vlad_project.repository.specification.CommentSpecification;
import it.aces.vlad_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final ArticleRepository articleRepository;
    private final VideoRepository videoRepository;
    private final EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public Page<CommentThinResponseDto> getAllComments(CommentFilterDto commentFilterDto, Pageable pageable) {
        log.debug("Получение списка Comment");
        return commentRepository.findAll(CommentSpecification.filter(commentFilterDto), pageable)
                .map(commentMapper::toThinDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(UUID id) {
        log.debug("Получение Comment по id = {}", id);
        CommentEntity comment = commentRepository.findEntityById(id, CommentEntity.class);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto createComment(CommentCreateDto commentCreateDto, String email) {
        log.debug("Создание Comment на основе Dto: {}", commentCreateDto);
        if (commentCreateDto.getArticleId() == null && commentCreateDto.getVideoId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Комментарий должен быть оставлен к видео или посту");
        }
        CommentEntity comment = commentMapper.toEntity(commentCreateDto);
        UserEntity user = userRepository.findByEmail(email);
        if (commentCreateDto.getArticleId() != null) {
            ArticleEntity article = articleRepository.findEntityById(commentCreateDto.getArticleId(), ArticleEntity.class);
            comment.setArticle(article);
        }
        if (commentCreateDto.getVideoId() != null) {
            VideoEntity video = videoRepository.findEntityById(commentCreateDto.getVideoId(), VideoEntity.class);
            comment.setVideo(video);
        }
        comment.setUser(user);
        CommentEntity createdComment = commentRepository.save(comment);
        emailService.sendEmail(1, email, CommentEntity.class.getSimpleName());
        return commentMapper.toDto(createdComment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(UUID id, CommentUpdateDto commentUpdateDto, String email) {
        log.debug("Редактирование Comment на основе Dto: {}", commentUpdateDto);
        CommentEntity comment = commentRepository.findEntityById(id, CommentEntity.class);
        permissionService.checkOwnerOrAdmin(comment, email, CommentEntity::getUser);
        if (commentUpdateDto.getComment() != null && !commentUpdateDto.getComment().isEmpty()) {
            comment.setComment(commentUpdateDto.getComment());
        }
        CommentEntity updatedComment = commentRepository.save(comment);
        emailService.sendEmail(2, email, CommentEntity.class.getSimpleName());
        return commentMapper.toDto(updatedComment);
    }

    @Override
    @Transactional
    public void deleteComment(UUID id, String email) {
        log.debug("Удаление Comment по id = {}", id);
        CommentEntity comment = commentRepository.findEntityById(id, CommentEntity.class);
        permissionService.checkOwnerOrAdmin(comment, email, CommentEntity::getUser);
        commentRepository.deleteById(id);
        emailService.sendEmail(3, email, CommentEntity.class.getSimpleName());
    }
}
