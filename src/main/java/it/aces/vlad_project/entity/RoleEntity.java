package it.aces.vlad_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment("Идентификатор сущности")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Наименование роли")
    @Column(name = "title", nullable = false)
    private String title;

    @Comment("Описание роли")
    @Column(name = "description")
    private String description;
}
