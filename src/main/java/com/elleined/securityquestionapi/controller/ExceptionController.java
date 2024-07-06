package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.APIResponse;
import com.elleined.securityquestionapi.exception.SecurityQuestionException;
import com.elleined.securityquestionapi.exception.question.QuestionException;
import com.elleined.securityquestionapi.exception.resource.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceException ex) {
        var response = new APIResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            QuestionException.class,
            SecurityQuestionException.class
    })
    public ResponseEntity<APIResponse> handleBadRequestExceptions(SecurityQuestionException ex) {
        var responseMessage = new APIResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<APIResponse>> handleBindException(BindException ex) {
        List<APIResponse> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new APIResponse(HttpStatus.BAD_REQUEST, errorMessage))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
