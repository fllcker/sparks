package ru.fllcker.postsservice.vo;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePostDto {
    @Size(min = 2, max = 256, message = "Post text should be greater than 2 letters and less than 256 letters!")
    private String text;
}
