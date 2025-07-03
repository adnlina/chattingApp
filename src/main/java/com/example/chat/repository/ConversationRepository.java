package com.example.chat.repository;

import com.example.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Message, String> {
    
    @Query("SELECT m FROM Message m WHERE " +
           "(m.senderId = :user1 AND m.receiverId = :user2) OR " +
           "(m.senderId = :user2 AND m.receiverId = :user1) " +
           "ORDER BY m.timestamp ASC")
    List<Message> findMessagesBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);
    
    @Query("SELECT m FROM Message m WHERE " +
           "((m.senderId = :user1 AND m.receiverId = :user2) OR " +
           "(m.senderId = :user2 AND m.receiverId = :user1)) AND " +
           "m.timestamp > :lastTimestamp " +
           "ORDER BY m.timestamp ASC")
    List<Message> findNewMessagesBetweenUsers(@Param("user1") String user1, @Param("user2") String user2, @Param("lastTimestamp") Instant lastTimestamp);
    
    default void saveMessage(String sender, String receiver, Message message) {
        save(message);
    }
    
    default List<Message> getMessages(String user1, String user2) {
        return findMessagesBetweenUsers(user1, user2);
    }
    
    default List<Message> getNewMessages(String user1, String user2, Instant lastTimestamp) {
        return findNewMessagesBetweenUsers(user1, user2, lastTimestamp);
    }
} 