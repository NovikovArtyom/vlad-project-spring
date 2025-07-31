package it.aces.vlad_project.repository.specification;

import it.aces.vlad_project.dto.comment.CommentFilterDto;
import it.aces.vlad_project.entity.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class CommentSpecification {
    public static Specification<CommentEntity> filter(CommentFilterDto filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(filter.getSearch())
                    .map(q -> q.split("\\s+"))
                    .stream()
                    .flatMap(Arrays::stream)
                    .map(String::trim)
                    .filter(token -> !token.isBlank())
                    .map(String::toLowerCase)
                    .map(token -> String.format("%%%s%%", token))
                    .flatMap(expr -> {
                        List<Predicate> predicatesList = new ArrayList<>();
                        predicatesList.add(builder.like(builder.lower(root.get(CommentEntity_.COMMENT)), expr));
                        return predicatesList.stream();
                    })
                    .reduce(builder::or)
                    .map(predicates::add);

            Optional.ofNullable(filter.getArticleId())
                    .ifPresent(id -> {
                        Join<CommentEntity, ArticleEntity> articleJoin = root.join(CommentEntity_.ARTICLE);
                        predicates.add(builder.equal(articleJoin.get(ArticleEntity_.ID), id));
                    });

            Optional.ofNullable(filter.getVideoId())
                    .ifPresent(id -> {
                        Join<CommentEntity, VideoEntity> videoJoin = root.join(CommentEntity_.VIDEO);
                        predicates.add(builder.equal(videoJoin.get(VideoEntity_.ID), id));
                    });

            return predicates.stream()
                    .reduce(builder::and)
                    .orElseGet(builder::conjunction);
        };
    }
}
