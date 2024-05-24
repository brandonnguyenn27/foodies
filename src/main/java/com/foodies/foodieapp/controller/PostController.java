package com.foodies.foodieapp.controller;

import com.foodies.foodieapp.model.Post;
import com.foodies.foodieapp.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postServices.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postServices.getPostById(id));
    }


    @GetMapping("/search/by-title")
    public ResponseEntity<List<Post>> getPostByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postServices.findPostsByTitle(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postServices.updatePost(id, post));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postServices.getPostsByUserId(userId));
    }
}
