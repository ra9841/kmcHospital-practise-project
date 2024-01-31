package com.rabin.hospitalpractiseproject.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogPayloadAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(com.rabin.hospitalpractiseproject.annotation.LogPayloads)")
    public Object logPayloads(ProceedingJoinPoint pjp) throws Throwable {
        //before advice
        log.info("Request Body {} ", objectMapper.writeValueAsString(pjp.getArgs()));
        Object obj = pjp.proceed();
        //after advice
        log.info("Response Body {} ", objectMapper.writeValueAsString(pjp.getArgs()));
        return obj;
    }
}
