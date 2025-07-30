package it.aces.vlad_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
    default T findEntityById(UUID id, Class<T> entityClass) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("%s с данным id: %s не найден", entityClass.getSimpleName(), id)
        ));
    }

}
