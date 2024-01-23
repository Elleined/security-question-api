package com.elleined.securityquestionapi.exception.resource;

import com.elleined.securityquestionapi.exception.SecurityQuestionException;

public class ResourceException extends SecurityQuestionException {
    public ResourceException(String message) {
        super(message);
    }
}
