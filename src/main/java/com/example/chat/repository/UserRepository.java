package com.example.chat.repository;

import com.example.chat.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> usersByUsername = new ConcurrentHashMap<>();

    public User signup(String username, String password) {
        if (usersByUsername.containsKey(username)) {
            return null; // Username already exists
        }
        User user = new User(username, password);
        usersByUsername.put(username, user);
        return user;
    }

    public User login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }
} 