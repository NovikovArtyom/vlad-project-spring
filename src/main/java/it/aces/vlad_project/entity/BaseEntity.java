package it.aces.vlad_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment("Идентификатор сущности")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Идентификатор пользователя, создавшего сущность")
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
