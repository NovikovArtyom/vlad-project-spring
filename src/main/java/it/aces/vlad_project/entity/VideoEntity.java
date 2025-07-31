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
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity extends BaseEntity<UUID> {
    @Comment("Наименование видео")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("Ссылка на видео")
    @Column(name = "url", nullable = false)
    private String url;
}
