package com.rabin.hospitalpractiseproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
@Slf4j
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Configure date format to ISO-8601
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        log.info("JavaTimeModule registered with ObjectMapper");
        return objectMapper;
    }
}

