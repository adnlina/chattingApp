package com.example.chat.service;

import com.example.chat.model.Message;
import com.example.chat.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ChatService {
    private final ConversationRepository conversationRepository;

    @Autowired
    public ChatService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    /**
     * Save a message asynchronously between two users.
     */
    @Async
    public CompletableFuture<Message> saveMessageAsync(String sender, String receiver, Message message) {
        try {
            Thread.sleep(500); // Simulate delay
            message.setId(UUID.randomUUID().toString());
            message.setTimestamp(Instant.now());
            conversationRepository.saveMessage(sender, receiver, message);
            return CompletableFuture.completedFuture(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(new RuntimeException("Message saving interrupted", e));
        }
    }

    /**
     * Get all messages between two users.
     */
    public List<Message> getAllMessages(String user1, String user2) {
        return conversationRepository.getMessages(user1, user2);
    }

    /**
     * Get new messages between two users since a timestamp.
     */
    public List<Message> getNewMessages(String user1, String user2, Instant lastTimestamp) {
        return conversationRepository.getNewMessages(user1, user2, lastTimestamp);
    }
} 