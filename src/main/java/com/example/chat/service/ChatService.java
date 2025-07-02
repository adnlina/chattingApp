package com.example.chat.service;

import com.example.chat.model.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture; // For Promise-based async operations

@Service
public class ChatService {

    // In-memory storage for messages. In a real app, this would be a database.
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    /**
     * Simulates saving a message asynchronously.
     * Uses @Async to run in a separate thread, demonstrating asynchronous processing.
     * Returns a CompletableFuture, which is Java's equivalent of a Promise.
     *
     * @param message The message to save.
     * @return A CompletableFuture that will complete with the saved message.
     */
    @Async
    public CompletableFuture<Message> saveMessageAsync(Message message) {
        System.out.println("Backend: Simulating asynchronous message saving...");
        try {
            // Simulate a network/database delay
            Thread.sleep(1000); // 1 second delay
            message.setId(UUID.randomUUID().toString()); // Assign a unique ID
            message.setTimestamp(Instant.now()); // Set server-side timestamp
            messages.add(message);
            System.out.println("Backend: Message saved: " + message.getId());
            return CompletableFuture.completedFuture(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(new RuntimeException("Message saving interrupted", e));
        }
    }

    /**
     * Retrieves all messages.
     * This could also be made asynchronous if fetching from a slow data source.
     *
     * @return A list of all messages.
     */
    public List<Message> getAllMessages() {
        // Return a copy to prevent external modification of the internal list
        return new ArrayList<>(messages);
    }

    /**
     * Retrieves messages newer than a given timestamp.
     * @param lastTimestamp The timestamp to filter messages by.
     * @return A list of new messages.
     */
    public List<Message> getNewMessages(Instant lastTimestamp) {
        List<Message> newMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getTimestamp().isAfter(lastTimestamp)) {
                newMessages.add(message);
            }
        }
        return newMessages;
    }
} 