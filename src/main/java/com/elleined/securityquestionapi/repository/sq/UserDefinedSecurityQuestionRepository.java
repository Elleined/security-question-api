package com.elleined.securityquestionapi.repository.sq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDefinedSecurityQuestionRepository extends JpaRepository<UserDefinedSecurityQuestion, Integer> {

    @Query("SELECT udsq FROM UserDefinedSecurityQuestion udsq WHERE udsq.owner = :currentUser")
    Page<UserDefinedSecurityQuestion> findAll(@Param("currentUser") User currentUser, Pageable pageable);
}