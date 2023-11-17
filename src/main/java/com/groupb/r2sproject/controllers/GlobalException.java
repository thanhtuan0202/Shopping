package com.groupb.r2sproject.controllers;

import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
 
@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public String handle(HttpMessageNotReadableException ex) {
        return "Quantity must be a number";
    }
}
