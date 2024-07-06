package com.elleined.securityquestionapi.repository.sq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreDefinedSecurityQuestionRepository extends JpaRepository<PreDefinedSecurityQuestion, Integer> {

    @Query("SELECT pdsq FROM PreDefinedSecurityQuestion pdsq WHERE pdsq.owner = :currentUser")
    Page<PreDefinedSecurityQuestion> findAll(@Param("currentUser") User currentUser, Pageable pageable);
}