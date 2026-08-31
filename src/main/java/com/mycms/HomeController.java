package com.mycms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin/posts")
    public String adminPostList(@RequestParam(required = false) PostStatus status,
            Model model) {
        if(status == null) {
            model.addAttribute("posts", postService.getPosts());
        } else {
            model.addAttribute("posts", postService.getPostsByStatus(status));
        }
        return "admin/post-list";
    }

    @GetMapping("/admin/posts/{id}")
    public String adminPostDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "admin/post-detail";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postService.getPublishedPosts());
        return "home";
    }

    @GetMapping("/admin/posts/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "admin/post-form";
    }

    @PostMapping("/admin/posts")
    public  String createPost(@RequestParam String title,
                              @RequestParam String content,
                              @RequestParam PostStatus status) {
        postService.createPost(title, content, status);
        return "redirect:/admin/posts";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post",postService.getPublishedPost(id));
        return "post-detail";
    }

    @GetMapping("/admin/posts/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "admin/post-edit";
    }

    @PostMapping("/admin/posts/{id}")
    public String updatePost(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String content,
                             @RequestParam PostStatus status) {
        postService.updatePost(id, title, content, status);
        return "redirect:/admin/posts";
    }

    @PostMapping("/admin/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/admin/posts";
    }
}
