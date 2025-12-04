package com.example.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public ChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 👇 IMPORTANT: name is getReply (not getAnswer)
    public String getReply(String userMessage) {

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey); // Authorization: Bearer <key>

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4.1-mini"); // or another model

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of(
                "role", "system",
                "content", "You are a friendly chatbot helping a student learn Java and Spring Boot. " +
                        "Answer clearly in simple language."
        ));
        messages.add(Map.of(
                "role", "user",
                "content", userMessage
        ));
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null) {
                return "Sorry, I couldn't reach the AI service.";
            }

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) responseBody.get("choices");
            if (choices == null || choices.isEmpty()) {
                return "Sorry, I didn't get any answer from the AI.";
            }

            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> message =
                    (Map<String, Object>) firstChoice.get("message");

            String content = (String) message.get("content");
            return content != null ? content.trim()
                    : "Sorry, I got an empty answer from the AI.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error while calling the AI: " + e.getMessage();
        }
    }
}
