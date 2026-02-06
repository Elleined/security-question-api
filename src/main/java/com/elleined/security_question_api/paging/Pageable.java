package com.elleined.security_question_api.paging;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.Collection;

public record Pageable<T>(
        @JsonProperty("content") Collection<T> content,
        @JsonProperty("page_request") PageRequest request,
        @JsonProperty("total_elements") int totalElements
) {

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return (totalElements + request.size() - 1) / request.size();
    }

    @JsonProperty("has_next_page")
    public boolean hasNextPage() {
        return request.page() < getTotalPages();
    }

    @JsonProperty("has_previous_page")
    public boolean hasPreviousPage() {
        return request.page() > 1 && getTotalPages() > 0;
    }

    @JsonProperty("next_page")
    public @Nullable Integer getNextPage() {
        return hasNextPage() ? request.page() + 1 : null;
    }

    @JsonProperty("previous_page")
    public @Nullable Integer getPreviousPage() {
        return hasPreviousPage() ? request.page() - 1 : null;
    }

    public static <T> Pageable<T> of(Collection<T> content, PageRequest request, int totalElements) {
        return new Pageable<>(content, request, totalElements);
    }
}
