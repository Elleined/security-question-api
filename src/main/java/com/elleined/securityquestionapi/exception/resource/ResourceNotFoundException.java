package com.elleined.securityquestionapi.exception.resource;

import com.elleined.securityquestionapi.exception.SecurityQuestionException;

public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
