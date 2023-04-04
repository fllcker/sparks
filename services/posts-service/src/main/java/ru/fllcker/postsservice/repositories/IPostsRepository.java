package ru.fllcker.postsservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.postsservice.models.Post;

@Repository
public interface IPostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findPostsByOwnerId(Long ownerId, Pageable pageable);
}
