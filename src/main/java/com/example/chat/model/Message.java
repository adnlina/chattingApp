package com.example.chat.model;

import jakarta.persistence.*;
import java.time.Instant; // Use Instant for modern timestamp handling

@Entity
@Table(name = "messages")
public class Message {
    @Id
    private String id;
    
    @Column(nullable = false)
    private String senderId;
    
    @Column(nullable = false)
    private String receiverId;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private Instant timestamp; // Using Instant for timestamp

    public Message() {
        // Default constructor for JSON deserialization
    }

    public Message(String id, String senderId, String receiverId, String content, Instant timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
               "id='" + id + '\'' +
               ", senderId='" + senderId + '\'' +
               ", receiverId='" + receiverId + '\'' +
               ", content='" + content + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
} 