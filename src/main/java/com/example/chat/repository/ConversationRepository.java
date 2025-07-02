package com.example.chat.repository;

import com.example.chat.model.Message;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ConversationRepository {
    // Key: sorted user pair (user1|user2), Value: list of messages
    private final Map<String, List<Message>> conversations = new ConcurrentHashMap<>();

    private String getConversationKey(String user1, String user2) {
        List<String> users = Arrays.asList(user1, user2);
        Collections.sort(users);
        return users.get(0) + "|" + users.get(1);
    }

    public void saveMessage(String sender, String receiver, Message message) {
        String key = getConversationKey(sender, receiver);
        conversations.computeIfAbsent(key, k -> Collections.synchronizedList(new ArrayList<>())).add(message);
    }

    public List<Message> getMessages(String user1, String user2) {
        String key = getConversationKey(user1, user2);
        return new ArrayList<>(conversations.getOrDefault(key, Collections.emptyList()));
    }

    public List<Message> getNewMessages(String user1, String user2, Instant lastTimestamp) {
        List<Message> all = getMessages(user1, user2);
        List<Message> result = new ArrayList<>();
        for (Message m : all) {
            if (m.getTimestamp().isAfter(lastTimestamp)) {
                result.add(m);
            }
        }
        return result;
    }
} 