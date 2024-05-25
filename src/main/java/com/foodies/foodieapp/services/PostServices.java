package com.foodies.foodieapp.services;

import com.foodies.foodieapp.model.Post;
import com.foodies.foodieapp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setImageUrl(updatedPost.getImageUrl());
            post.setLocation(updatedPost.getLocation());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getPostsByUserId(String id) {
        return postRepository.findByUserId(id);
    }

    public List<Post> findPostsByTitle(String title) {
        return postRepository.findByTitle(title);
    }




}
