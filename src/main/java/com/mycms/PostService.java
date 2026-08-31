package com.mycms;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPublishedPosts() {
        return postRepository.findByStatus(PostStatus.PUBLISHED);
    }

    public void createPost(String title, String content, PostStatus status) {
        postRepository.save(new Post(title, content, status));
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void updatePost(Long id, String title, String content, PostStatus status) {
        Post post = postRepository.findById(id).orElseThrow();
        post.update(title, content, status);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getPostsByStatus(PostStatus status) {
        return postRepository.findByStatus(status);
    }

    public Post getPublishedPost(Long id) {
        return postRepository.findByIdAndStatus(id, PostStatus.PUBLISHED)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "게시글을 찾을수 없어요."
                        ));
    }
}
