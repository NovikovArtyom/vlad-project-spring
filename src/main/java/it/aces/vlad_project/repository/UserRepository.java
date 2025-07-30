package it.aces.vlad_project.repository;

import it.aces.vlad_project.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
}
