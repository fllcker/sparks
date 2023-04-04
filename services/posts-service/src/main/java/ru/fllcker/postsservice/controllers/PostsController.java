package ru.fllcker.postsservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.postsservice.models.Post;
import ru.fllcker.postsservice.security.services.AuthService;
import ru.fllcker.postsservice.services.PostsService;
import ru.fllcker.postsservice.vo.CreatePostDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostsController {
    private final PostsService postsService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDto createPostDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        String email = authService.getAuthInfo().getEmail();
        Post post = postsService.create(email, createPostDto);
        return ResponseEntity.ok(post);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Page<Post>> findByOwnerId(@PathVariable Long userId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || limit == null)
            return ResponseEntity.ok(postsService.findByOwnerId(userId));
        else
            return ResponseEntity.ok(postsService.findByOwnerId(userId, page, limit));
    }
}
