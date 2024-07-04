package com.hisujung.microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface GptService {
    String getAssistantMsg(String activities, String careerField) throws JsonProcessingException;
}
