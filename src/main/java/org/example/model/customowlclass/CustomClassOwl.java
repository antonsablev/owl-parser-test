package org.example.model.customowlclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.semanticweb.owlapi.model.IRI;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CustomClassOwl {
    private IRI iri;
    private Set<CustomClassAnnotation> annotations;
    private Set<CustomClassOwl> parents;

    public String getIriFragment() {
        return iri.getFragment();
    }

    public Set<String> getParentsFragment() {
        return parents.stream()
                .map(p -> p.getIri().getFragment())
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "\nCustomOwlClass{" +
                "\n\tiri= " + getIriFragment() +
                ",\n\tannotations=" + annotations +
                ",\n\tparents=" + getParentsFragment() +
                "\n}";
    }

}
