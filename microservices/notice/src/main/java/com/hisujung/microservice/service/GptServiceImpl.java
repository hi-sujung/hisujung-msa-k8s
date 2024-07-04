package com.hisujung.microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GptServiceImpl implements GptService{
    @Value("${openai.api.key}")
    private String apiKey;

    public JsonNode callChatGpt(String activities, String careerField) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        System.out.println("키: " + apiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "gpt-4");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", activities);
        messages.add(userMessage);

        Map<String, String> assistantMessage = new HashMap<>();
        assistantMessage.put("role", "system");
        assistantMessage.put("content", "내가 참여한 대외활동은 다음과 같고, 내가 취업하려는 분야는" + careerField + ", 이러한 내용을 참고해서 나의 취업 자기소개서를 5000자 이내로 작성해줘.");
        messages.add(assistantMessage);

        bodyMap.put("messages", messages);

        String body = objectMapper.writeValueAsString(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return objectMapper.readTree(response.getBody());
    }

    @Override
    public String getAssistantMsg(String activities, String careerField) throws JsonProcessingException {
        JsonNode jsonNode = callChatGpt(activities, careerField);
        String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

        return content;
    }
}
