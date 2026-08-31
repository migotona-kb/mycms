package com.mycms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status);

    Optional<Post> findByIdAndStatus(Long id, PostStatus status);
}
