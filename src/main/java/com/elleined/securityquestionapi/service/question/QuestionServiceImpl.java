package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.mapper.QuestionMapper;
import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question save(String question) {
        Question createdQuestion = questionMapper.toEntity(question);
        questionRepository.save(createdQuestion);
        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public List<Question> save(List<String> questions) {
        List<Question> questionList = questions.stream()
                .map(questionMapper::toEntity)
                .toList();

        questionRepository.saveAll(questionList);
        log.debug("Questions with ids of {} saved successfully", questionList.stream().map(Question::getId).toList());
        return questionList;
    }

    @Override
    public Question getById(int id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }
}
