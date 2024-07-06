package com.elleined.securityquestionapi.repository.question;

import com.elleined.securityquestionapi.model.question.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
}