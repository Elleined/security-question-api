package com.elleined.securityquestionapi.dto;

import org.springframework.hateoas.RepresentationModel;

abstract class HATEOUSLinker<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {
    public abstract T addLinks();
    abstract T addSelfLinks();
    abstract T addRelatedLinks();
}
