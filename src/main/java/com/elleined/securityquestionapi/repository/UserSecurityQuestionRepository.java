package com.elleined.securityquestionapi.repository;

import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityQuestionRepository extends JpaRepository<UserSecurityQuestion, Integer> {
}