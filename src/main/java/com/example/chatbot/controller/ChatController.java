package com.example.chatbot.controller;

import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.dto.ChatResponse;
import com.example.chatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // allow calls from any frontend (React, HTML, etc.)
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // POST /api/chat
    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String reply = chatService.getReply(request.getMessage());
        return new ChatResponse(reply);
    }

    // GET /api/chat/ping
    @GetMapping("/ping")
    public String ping() {
        return "Chatbot API is up ✅";
    }
}
