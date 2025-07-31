package it.aces.vlad_project.service;

import it.aces.vlad_project.dto.comment.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {
    Page<CommentThinResponseDto> getAllComments(CommentFilterDto commentFilterDto, Pageable pageable);

    CommentResponseDto getCommentById(UUID id);

    CommentResponseDto createComment(CommentCreateDto commentCreateDto, String email);

    CommentResponseDto updateComment(UUID id, CommentUpdateDto commentUpdateDto, String email);

    void deleteComment(UUID id, String email);
}
