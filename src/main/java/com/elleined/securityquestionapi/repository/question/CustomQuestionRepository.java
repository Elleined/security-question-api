package com.elleined.securityquestionapi.repository.question;

import com.elleined.securityquestionapi.model.question.UserDefinedSecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomQuestionRepository extends JpaRepository<UserDefinedSecurityQuestion, Integer> {
}