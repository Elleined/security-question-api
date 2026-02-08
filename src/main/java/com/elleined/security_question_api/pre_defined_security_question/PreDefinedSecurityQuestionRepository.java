package com.elleined.security_question_api.pre_defined_security_question;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PreDefinedSecurityQuestionRepository extends CrudRepository<PreDefinedSecurityQuestion, UUID> {

    @Query("CALL pre_defined_security_question_find_all(:resourceId, :page, :size)")
    List<PreDefinedSecurityQuestionDTO> findAll(@Param("resourceId") UUID resourceId,
                                                @Param("page") int page,
                                                @Param("size") int size);

    @Query("SELECT pre_defined_security_question_find_all_total(:resourceId)")
    int findAllTotal(@Param("resourceId") UUID resourceId);

    @Query("SELECT pre_defined_security_question_find_answer(:resourceId, :securityQuestionId)")
    String findAnswer(@Param("resourceId") UUID resourceId,
                      @Param("securityQuestionId") UUID securityQuestionId);

    @Query("SELECT pre_defined_security_question_exists(:resourceId, :securityQuestionId)")
    boolean isExists(@Param("resourceId") UUID resourceId,
                     @Param("securityQuestionId") UUID securityQuestionId);

    @Modifying
    @Transactional
    @Query("CALL pre_defined_security_question_save(:resourceId, :securityQuestionId, :answer)")
    void save(@Param("resourceId") UUID resourceId,
              @Param("securityQuestionId") UUID securityQuestionId,
              @Param("answer") String answer);

    @Modifying
    @Transactional
    @Query("CALL pre_defined_security_question_update_answer(:resourceId, :securityQuestionId, :answer)")
    void updateAnswer(@Param("resourceId") UUID resourceId,
                      @Param("securityQuestionId") UUID securityQuestionId,
                      @Param("answer") String answer);

}
