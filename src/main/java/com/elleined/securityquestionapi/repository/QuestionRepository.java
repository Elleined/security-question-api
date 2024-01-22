package com.elleined.securityquestionapi.repository;

import com.elleined.securityquestionapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}