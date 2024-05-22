package com.foodies.foodieapp.repositories;
import com.foodies.foodieapp.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
