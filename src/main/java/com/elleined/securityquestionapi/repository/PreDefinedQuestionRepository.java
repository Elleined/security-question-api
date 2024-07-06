package com.elleined.securityquestionapi.repository;

import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreDefinedQuestionRepository extends JpaRepository<PreDefinedQuestion, Integer> {
}