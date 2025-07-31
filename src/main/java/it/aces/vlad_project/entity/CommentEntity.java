package it.aces.vlad_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseEntity<UUID> {
    @Comment("Комментарий")
    @Column(nullable = false)
    private String comment;

    @Comment("Идентификатор поста, к которому оставлен комментарий")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_comment_article"))
    private ArticleEntity article;

    @Comment("Идентификатор видео, к которому оставлен комментарий")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", foreignKey = @ForeignKey(name = "fk_comment_video"))
    private VideoEntity video;
}
