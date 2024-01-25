package com.elleined.securityquestionapi.repository.question;

import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreDefinedQuestionRepository extends JpaRepository<PreDefinedQuestion, Integer> {
}