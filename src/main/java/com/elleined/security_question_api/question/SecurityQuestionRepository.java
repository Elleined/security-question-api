package com.elleined.security_question_api.question;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface SecurityQuestionRepository extends CrudRepository<SecurityQuestion, UUID> {

    @Query("CALL security_question_find_all(:name, :page, :size)")
    List<SecurityQuestionDTO> findAll(@Param("name") String name,
                                      @Param("page") int page,
                                      @Param("size") int size);


    @Query("SELECT security_question_find_all_total(:name)")
    int findAllTotal(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("CALL security_question_save(:name)")
    void save(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("CALL security_question_update(:id, :name)")
    void update(@Param("id") UUID id,
                @Param("name") String name);


    @Modifying
    @Transactional
    @Query("CALL security_question_delete(:id, :name)")
    void delete(@Param("id") UUID id);
}
