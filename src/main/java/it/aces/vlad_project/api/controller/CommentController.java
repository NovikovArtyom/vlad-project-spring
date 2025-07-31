package it.aces.vlad_project.api.controller;

import it.aces.vlad_project.api.CommentApi;
import it.aces.vlad_project.dto.comment.*;
import it.aces.vlad_project.service.CommentService;
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
public class CommentController implements CommentApi {
    private final CommentService commentService;

    @Override
    public ResponseEntity<ApiResponse<CommentThinResponseDto>> getAllComments(CommentFilterDto commentFilterDto, Pageable pageable) {
        log.debug("Запрос на получение списка Comment");
        Page<CommentThinResponseDto> page = commentService.getAllComments(commentFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(page));
    }

    @Override
    public ResponseEntity<CommentResponseDto> getCommentById(UUID id) {
        log.debug("Запрос на получение Comment по id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentById(id));
    }

    @Override
    public ResponseEntity<CommentResponseDto> createComment(CommentCreateDto commentCreateDto, Authentication authentication) {
        log.debug("Запрос на создание Comment: {}", commentCreateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentCreateDto, email));
    }

    @Override
    public ResponseEntity<CommentResponseDto> updateComment(UUID id, CommentUpdateDto commentUpdateDto, Authentication authentication) {
        log.debug("Запрос на редактирование Comment: {}", commentUpdateDto);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id, commentUpdateDto, email));
    }

    @Override
    public ResponseEntity<Void> deleteComment(UUID id, Authentication authentication) {
        log.debug("Запрос на удаление Comment: {}", id);
        String email = authentication.getName();
        commentService.deleteComment(id, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
