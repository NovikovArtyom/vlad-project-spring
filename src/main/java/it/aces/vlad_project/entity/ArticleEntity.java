package it.aces.vlad_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity extends BaseEntity<UUID> {
    @Comment("Наименование поста")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("Содержание поста")
    @Column(name = "article", nullable = false)
    private String article;
}
