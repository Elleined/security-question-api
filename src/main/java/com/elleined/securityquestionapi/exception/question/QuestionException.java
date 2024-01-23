package com.elleined.securityquestionapi.exception.question;

import com.elleined.securityquestionapi.exception.SecurityQuestionException;

public class QuestionException extends SecurityQuestionException {
    public QuestionException(String message) {
        super(message);
    }
}
