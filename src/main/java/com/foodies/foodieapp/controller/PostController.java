package com.foodies.foodieapp.controller;

import com.foodies.foodieapp.model.Post;
import com.foodies.foodieapp.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal OAuth2User principal, @RequestBody Post post) {
        if (principal == null || principal.getAttribute("sub") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String userId = principal.getAttribute("sub");
        try {
            Post createdPost = postServices.createPost(post, userId);
            return ResponseEntity.ok(createdPost);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postServices.getPostById(id));
    }


    @GetMapping("/search/by-title")
    public ResponseEntity<List<Post>> getPostByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postServices.findPostsByTitle(title));
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<Post>> getMyPosts(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String userId = principal.getAttribute("sub");
        List<Post> posts = postServices.getPostsByUserId(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postServices.updatePost(id, post));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(postServices.getPostsByUserId(userId));
    }
}
