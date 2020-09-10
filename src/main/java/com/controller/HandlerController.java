package com.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {
    @ExceptionHandler(DuplicateManException.class)
    public String duplicateManHandler() {
        return "duplicateMan";
    }
}
