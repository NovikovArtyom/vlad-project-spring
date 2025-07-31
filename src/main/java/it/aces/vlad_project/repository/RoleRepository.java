package it.aces.vlad_project.repository;

import it.aces.vlad_project.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity> {
    RoleEntity findByTitle(String title);
}
