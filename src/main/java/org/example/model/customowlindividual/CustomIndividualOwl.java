package org.example.model.customowlindividual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.semanticweb.owlapi.model.IRI;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CustomIndividualOwl {
    private IRI iri;
    private Set<CustomIndividualsAnnotation> annotations;

    public String getIriFragment() {
        return iri.getFragment();
    }

    @Override
    public String toString() {
        return "\nCustomOwlIndividual{" +
                "iri = " + getIriFragment() +
                ",\nannotations = " + annotations +
                "\n}";
    }
}
