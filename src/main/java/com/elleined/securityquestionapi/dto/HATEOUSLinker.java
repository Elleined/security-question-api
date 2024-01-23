package com.elleined.securityquestionapi.dto;

public interface HATEOUSLinker<T> {
    T addLinks();
    T addSelfLinks();
    T addRelatedLinks();
}
