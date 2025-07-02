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

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Send a message from sender to receiver.
     * senderId and receiverId must be present in the Message object.
     */
    @PostMapping
    public CompletableFuture<ResponseEntity<Message>> sendMessage(@RequestBody Message message) {
        if (message.getSenderId() == null || message.getReceiverId() == null) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return chatService.saveMessageAsync(message.getSenderId(), message.getReceiverId(), message)
                .thenApply(savedMessage -> new ResponseEntity<>(savedMessage, HttpStatus.CREATED))
                .exceptionally(ex -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Get all messages between two users.
     */
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(@RequestParam String user1, @RequestParam String user2) {
        List<Message> messages = chatService.getAllMessages(user1, user2);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Get new messages between two users since a timestamp.
     */
    @GetMapping("/latest")
    public ResponseEntity<List<Message>> getNewMessages(@RequestParam String user1, @RequestParam String user2, @RequestParam long timestampMs) {
        Instant lastTimestamp = Instant.ofEpochMilli(timestampMs);
        List<Message> newMessages = chatService.getNewMessages(user1, user2, lastTimestamp);
        return new ResponseEntity<>(newMessages, HttpStatus.OK);
    }
}
