package com.example.demo.exceptions;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private String message;
    private LocalDateTime dateTime;
}