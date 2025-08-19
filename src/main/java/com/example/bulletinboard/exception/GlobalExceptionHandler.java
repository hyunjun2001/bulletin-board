package com.example.bulletinboard.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // DataNotFoundException 처리
    @ExceptionHandler(DataNotFoundException.class)
    public String handleDataNotFound(DataNotFoundException ex, HttpServletRequest request) {
        log.error("데이터 없음 예외 발생: URI={}, Message={}", request.getRequestURI(), ex.getMessage(), ex);

        // 커스텀 오류 페이지
        return "/error";
    }

    // 다른 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, HttpServletRequest request) {
        log.error("서버 예외 발생: URI={}, Message={}", request.getRequestURI(), ex.getMessage(), ex);

        return "/error";
    }
}
