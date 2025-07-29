package it.aces.vlad_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity<UUID> {
    @Comment("Почта пользователя")
    @Column(name = "email", nullable = false)
    private String email;

    @Comment("Пароль пользователя")
    @Column(name = "password", nullable = false)
    private String password;
}
