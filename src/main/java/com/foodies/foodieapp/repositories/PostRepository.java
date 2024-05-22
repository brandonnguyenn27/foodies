package com.foodies.foodieapp.repositories;
import com.foodies.foodieapp.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
