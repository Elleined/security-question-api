package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.APIResponse;
import com.elleined.securityquestionapi.exception.SecurityQuestionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SecurityQuestionException.class)
    public ResponseEntity<APIResponse> handleBadRequestExceptions(SecurityQuestionException ex) {
        var responseMessage = new APIResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
