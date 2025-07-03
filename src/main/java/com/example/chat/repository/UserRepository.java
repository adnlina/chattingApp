package com.example.chat.repository;

import com.example.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    
    default User signup(String username, String password) {
        if (findByUsername(username).isPresent()) {
            return null; // Username already exists
        }
        User user = new User(username, password);
        return save(user);
    }
    
    default User login(String username, String password) {
        return findByUsernameAndPassword(username, password).orElse(null);
    }
} 