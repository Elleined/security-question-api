package com.elleined.securityquestionapi.dto;

import org.springframework.hateoas.RepresentationModel;

abstract class HateousLinker<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {
    public void addLinks() {
        addSelfLinks();
        addRelatedLinks();
    }

    public abstract void addSelfLinks();

    public abstract void addRelatedLinks();
}
