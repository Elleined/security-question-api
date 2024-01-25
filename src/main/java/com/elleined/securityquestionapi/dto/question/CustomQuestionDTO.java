package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.controller.question.CustomQuestionController;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class CustomQuestionDTO extends QuestionDTO {
    private int ownerId;
    private LocalDateTime createdAt;
    private String answer;

    @Builder(builderMethodName = "customQuestionDTOBuilder")
    public CustomQuestionDTO(int id, String question, int ownerId, LocalDateTime createdAt, String answer) {
        super(id, question);
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.answer = answer;
        addLinks();
    }

    @Override
    public void addSelfLinks() {
        this.add(
                linkTo(methodOn(CustomQuestionController.class).getAll(null))
                        .withSelfRel()
                        .withTitle("Get all question by owner")
                        .withType("GET"),
                linkTo(methodOn(CustomQuestionController.class).isAnswerCorrect(null, getId(), null))
                        .withSelfRel()
                        .withTitle("Check if security question answer is correct")
                        .withType("GET"),
                linkTo(methodOn(CustomQuestionController.class).save(null, null, null))
                        .withSelfRel()
                        .withTitle("Create security question")
                        .withType("POST")
        );
    }

    @Override
    public void addRelatedLinks() {

    }
}
