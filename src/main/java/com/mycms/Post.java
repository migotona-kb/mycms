package com.mycms;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(STRING)
    private PostStatus status;

    @Lob
    private String content;

    public Post(String title, String content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = status;
    }

    protected Post() {
    }

    public void update(String title, String content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PostStatus getStatus() {
        return status;
    }
}
