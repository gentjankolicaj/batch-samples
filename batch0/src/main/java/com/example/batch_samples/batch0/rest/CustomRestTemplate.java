package com.example.batch_samples.batch0.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableRetry
public class CustomRestTemplate {

    private RestTemplate restTemplate;

    public CustomRestTemplate() {
    }

    @Bean
    public RestTemplate getRestTemplate() {
        this.restTemplate = new RestTemplate();
        return this.restTemplate;
    }


    @Recover
    public String fallbackMethod() {
        return "Error after http request is sent. ";
    }

    @CircuitBreaker
    public String sendRequest() {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080",
                HttpMethod.GET,
                null,
                String.class);
        return responseEntity.getBody();
    }


}
