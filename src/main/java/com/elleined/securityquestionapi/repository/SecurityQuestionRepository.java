package com.elleined.securityquestionapi.repository;

import com.elleined.securityquestionapi.model.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
}