package com.rabin.hospitalpractiseproject.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Aspect
@Component
@Slf4j
public class LoggingAdvice {


    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("execution(* com.rabin.hospitalpractiseproject.service.PatientServiceImpl.*(..))")
    private void logPointcut() {
    }

    @Before("logPointcut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("Before Advice - class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Before Advice - Request Body {} ", objectMapper.writeValueAsString(joinPoint.getArgs()));
        //new ObjectMapper()--- if using this then there will be conflict between jackson config & give error.if not using LocalDate then you can use it
    }

//    @AfterReturning(value = "execution (* com.rabin.hospitalpractiseproject.controller.PatientController.*(..))",returning = "object")
//    public void logResponse(JoinPoint joinPoint,Object object) throws JsonProcessingException {
//        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
//        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(object));
//    }

    @AfterReturning(value = "execution (* com.rabin.hospitalpractiseproject.service.PatientServiceImpl.*(..))")
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("After Advice - LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("After Advice - LoggingAdvice::logResponse Response Body {} ", objectMapper.writeValueAsString(joinPoint.getArgs()));
    }


    @AfterThrowing(value = "execution (* com.rabin.hospitalpractiseproject.service.PatientServiceImpl.*(..))")
    public void logError(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("Throws Advice - LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Throws Advice - LoggingAdvice::logResponse Response Body {} ", objectMapper.writeValueAsString(joinPoint.getArgs()));
    }
}
