package ru.fllcker.postsservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.fllcker.postsservice.feign.UsersClient;
import ru.fllcker.postsservice.models.Post;
import ru.fllcker.postsservice.repositories.IPostsRepository;
import ru.fllcker.postsservice.vo.CreatePostDto;
import ru.fllcker.postsservice.vo.User;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {
    private final IPostsRepository postsRepository;
    private final UsersClient usersClient;

    public Post create(String email, CreatePostDto dto) {
        User owner = usersClient.findByEmail(email);
        Post post = new Post(dto.getText(), owner.getId());
        return postsRepository.save(post);
    }

    public Page<Post> findByOwnerId(Long ownerId) {
        return findByOwnerId(ownerId, 0, 10);
    }

    public Page<Post> findByOwnerId(Long ownerId, int page, int size) {
        return postsRepository.findPostsByOwnerId(ownerId, PageRequest.of(page, size, Sort.by("createdTime").descending()));
    }
}
