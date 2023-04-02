package ru.fllcker.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
