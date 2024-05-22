package com.foodies.foodieapp.services;

import com.foodies.foodieapp.model.Post;
import com.foodies.foodieapp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostsByUserId(Long id) {
        return postRepository.findByUserId(id);
    }
}
