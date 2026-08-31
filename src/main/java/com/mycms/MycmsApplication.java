package com.mycms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MycmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycmsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PostRepository postRepository) {
        return args -> {
            postRepository.save(new Post("1 번째 글입니다", "1 번쨰 글의 본문입니다", PostStatus.DRAFT));
            postRepository.save(new Post("2 번째 글입니다", "2 번쨰 글의 본문입니다", PostStatus.DRAFT));
            postRepository.save(new Post("3 번째 글입니다", "3 번쨰 글의 본문입니다", PostStatus.PUBLISHED));
        };
    }
}
