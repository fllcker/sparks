package ru.fllcker.postsservice.vo;

import lombok.*;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class User {
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private String description;
}
