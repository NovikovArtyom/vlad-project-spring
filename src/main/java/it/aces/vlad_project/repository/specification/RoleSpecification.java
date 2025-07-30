package it.aces.vlad_project.repository.specification;

import it.aces.vlad_project.dto.role.RoleFilterDto;
import it.aces.vlad_project.entity.RoleEntity;
import it.aces.vlad_project.entity.RoleEntity_;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;

@NoArgsConstructor
public class RoleSpecification {
    public static Specification<RoleEntity> filter(RoleFilterDto filter) {
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
                        predicatesList.add(builder.like(builder.lower(root.get(RoleEntity_.TITLE)), expr));
                        predicatesList.add(builder.like(builder.lower(root.get(RoleEntity_.DESCRIPTION)), expr));
                        return predicatesList.stream();
                    })
                    .reduce(builder::or)
                    .map(predicates::add);

            return predicates.stream()
                    .reduce(builder::and)
                    .orElseGet(builder::conjunction);
        };
    }
}
