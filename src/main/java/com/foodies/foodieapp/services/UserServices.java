package com.foodies.foodieapp.services;

import com.foodies.foodieapp.model.User;
import com.foodies.foodieapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    //need to create passwordEncoder util

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User processsUserOAuthLogin(OAuth2User oAuth2User) {
        String oAuthId = oAuth2User.getAttribute("sub");
        return userRepository.findById(oAuthId)
                .orElseGet(() -> createUser(oAuth2User));
    }
    public User createUser(OAuth2User oAuth2User) {
        User newUser = new User();
        newUser.setEmail(oAuth2User.getAttribute("email"));
        newUser.setUsername(oAuth2User.getAttribute("name"));
        newUser.setId(oAuth2User.getAttribute("sub"));
        return userRepository.save(newUser);
    }

    public User updateUser(String id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
            if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(String id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            throw new RuntimeException("User not found with id: " + id);
        });
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }



}
