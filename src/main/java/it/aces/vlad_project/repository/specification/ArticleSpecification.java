package it.aces.vlad_project.repository.specification;

import it.aces.vlad_project.dto.article.ArticleFilterDto;
import it.aces.vlad_project.entity.ArticleEntity;
import it.aces.vlad_project.entity.ArticleEntity_;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class ArticleSpecification {
    public static Specification<ArticleEntity> filter(ArticleFilterDto filter) {
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

            Optional.ofNullable(filter.getName())
                    .ifPresent(name -> predicates.add(builder.equal(root.get(ArticleEntity_.NAME), name)));

            return predicates.stream()
                    .reduce(builder::and)
                    .orElseGet(builder::conjunction);
        };
    }
}
