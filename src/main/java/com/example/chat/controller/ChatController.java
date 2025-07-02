package com.example.chat.controller;

import com.example.chat.model.Message;
import com.example.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*") // Allow requests from any origin for development
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Endpoint to send a new message.
     * Demonstrates using CompletableFuture from an @Async service method.
     *
     * @param message The message object received from the frontend.
     * @return ResponseEntity with the saved message or an error.
     */
    @PostMapping
    public CompletableFuture<ResponseEntity<Message>> sendMessage(@RequestBody Message message) {
        System.out.println("Backend: Received message to send: " + message.getContent());
        // Call the async service method and then process its result
        return chatService.saveMessageAsync(message)
                .thenApply(savedMessage -> {
                    System.out.println("Backend: Message sent response prepared.");
                    return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
                })
                .exceptionally(ex -> {
                    System.err.println("Backend: Error sending message: " + ex.getMessage());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                });
    }

    /**
     * Endpoint to get all messages.
     *
     * @return A list of all messages.
     */
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        System.out.println("Backend: Fetching all messages.");
        List<Message> messages = chatService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Endpoint to get messages newer than a specific timestamp.
     * This can be used for simple polling.
     *
     * @param timestampMs The timestamp in milliseconds since epoch.
     * @return A list of new messages.
     */
    @GetMapping("/latest")
    public ResponseEntity<List<Message>> getNewMessages(@RequestParam long timestampMs) {
        System.out.println("Backend: Fetching new messages since: " + timestampMs);
        Instant lastTimestamp = Instant.ofEpochMilli(timestampMs);
        List<Message> newMessages = chatService.getNewMessages(lastTimestamp);
        return new ResponseEntity<>(newMessages, HttpStatus.OK);
    }
} 