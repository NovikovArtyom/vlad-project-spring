package it.aces.vlad_project.repository.specification;

import it.aces.vlad_project.dto.video.VideoFilterDto;
import it.aces.vlad_project.entity.ArticleEntity_;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.entity.UserEntity_;
import it.aces.vlad_project.entity.VideoEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class VideoSpecification {
    public static Specification<VideoEntity> filter(VideoFilterDto filter) {
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
                        predicatesList.add(builder.like(builder.lower(root.get(ArticleEntity_.NAME)), expr));
                        predicatesList.add(builder.like(builder.lower(root.get(ArticleEntity_.ARTICLE)), expr));
                        return predicatesList.stream();
                    })
                    .reduce(builder::or)
                    .map(predicates::add);

            Optional.ofNullable(filter.getUserId())
                    .ifPresent(id -> {
                        Join<VideoEntity, UserEntity> userJoin = root.join(VideoEntity_.USER_ID);
                        predicates.add(builder.equal(userJoin.get(UserEntity_.ID), id));
                    });

            return predicates.stream()
                    .reduce(builder::and)
                    .orElseGet(builder::conjunction);
        };
    }
}
