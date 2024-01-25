package com.elleined.securityquestionapi.repository.question;

import com.elleined.securityquestionapi.model.question.CustomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomQuestionRepository extends JpaRepository<CustomQuestion, Integer> {
}