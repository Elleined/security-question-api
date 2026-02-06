package com.elleined.security_question_api.paging;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PageRequest(@JsonProperty("page") int page,
                          @JsonProperty("size") int size) {

    public PageRequest {
        if (page <= 0) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }
}
