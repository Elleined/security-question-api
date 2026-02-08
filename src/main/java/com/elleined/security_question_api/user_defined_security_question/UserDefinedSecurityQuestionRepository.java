package com.elleined.security_question_api.user_defined_security_question;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface UserDefinedSecurityQuestionRepository extends CrudRepository<UserDefinedSecurityQuestion, UUID> {

    @Query("CALL user_defined_security_question_find_all(:#{#resourceId.toString()}, :page, :size)")
    List<UserDefinedSecurityQuestionDTO> findAll(@Param("resourceId") UUID resourceId,
                                                 @Param("page") int page,
                                                 @Param("size") int size);

    @Query("SELECT user_defined_security_question_find_all_total(:#{#resourceId.toString()})")
    int findAllTotal(@Param("resourceId") UUID resourceId);

    @Query("SELECT user_defined_security_question_find_answer(:#{#id.toString()}, :#{#resourceId.toString()})")
    String findAnswer(@Param("id") UUID id,
                      @Param("resourceId") UUID resourceId);

    @Modifying
    @Transactional
    @Query("CALL user_defined_security_question_save(:#{#resourceId.toString()}, :question, :answer)")
    void save(@Param("resourceId") UUID resourceId,
              @Param("question") String question,
              @Param("answer") String answer);

    @Modifying
    @Transactional
    @Query("CALL user_defined_security_question_update_answer(:#{#id.toString()}, :#{#resourceId.toString()}, :answer)")
    void updateAnswer(@Param("id") UUID id,
                      @Param("resourceId") UUID resourceId,
                      @Param("answer") String answer);
}
