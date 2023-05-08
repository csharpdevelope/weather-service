package com.example.weather.service;

import com.example.weather.model.entity.RequestLog;
import com.example.weather.repository.RequestLogRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class HttpService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final RequestLogRepository requestLogRepository;
    @Value("${weather.url.base}")
    private String urlBase;
    @Value("${weather.api.key}")
    private String apiKey;

    public JsonNode requestService(String query) {
        long currentTime = System.currentTimeMillis();
        ObjectNode response = objectMapper.createObjectNode();
        logger.info("request sending");
        JsonNode jsonNode = null;
        String url = getUrlBase(query);
        int code = 200;
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);
            jsonNode = responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            logger.error("Error Message: {}", e.getMessage());
            code = e.getStatusCode().value();
            jsonNode = getResponseBody(e.getResponseBodyAsByteArray());
        } catch (Exception e) {
            logger.error("Time out: {}", e.getMessage());
            code = 408;
        } finally {
            long readTime = System.currentTimeMillis() - currentTime;
            RequestLog requestLog = new RequestLog();
            requestLog.setCode(code);
            requestLog.setPath(url);
            requestLog.setDuration(readTime);
            requestLog.setResponse(String.valueOf(jsonNode));
            requestLogRepository.save(requestLog);
        }
        return jsonNode;
    }

    private JsonNode getResponseBody(byte[] content) {
        JsonNode result = null;
        try {
            result = objectMapper.readValue(new String(content, StandardCharsets.UTF_8), JsonNode.class);
        } catch (Exception e) {
            logger.error("Response Body Read error: {}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private String getUrlBase(String query) {
        StringBuilder builder = new StringBuilder();
        builder.append(urlBase);
        builder.append("/current.json");
        builder.append("?key=");
        builder.append(apiKey);
        builder.append("&q=");
        builder.append(query);
        return builder.toString();
    }
}
