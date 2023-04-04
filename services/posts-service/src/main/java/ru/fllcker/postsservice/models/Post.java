package ru.fllcker.postsservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Instant createdTime;
    private Long ownerId;

    public Post(String text, Long ownerId) {
        this.text = text;
        this.ownerId = ownerId;
        createdTime = Instant.now();
    }

}
