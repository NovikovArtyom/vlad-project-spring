package it.aces.vlad_project.repository.specification;

import it.aces.vlad_project.dto.user.UserFilterDto;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.entity.UserEntity_;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class UserSpecification {
    public static Specification<UserEntity> filter(UserFilterDto filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(filter.getEmail())
                    .ifPresent(name -> predicates.add(builder.equal(root.get(UserEntity_.EMAIL), name)));

            return predicates.stream()
                    .reduce(builder::and)
                    .orElseGet(builder::conjunction);
        };
    }
}
