package org.example.model.customowlproperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.semanticweb.owlapi.model.IRI;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CustomPropertyOwl {
    private IRI iri;
    private Set<CustomPropertyAnnotation> annotations;

    public String getIriFragment() {
        return iri.getFragment();
    }

    @Override
    public String toString() {
        return "\nPropertyOwl{" +
                "iri=" + getIriFragment() +
                ",\nannotations=" + annotations +
                "}\n";
    }
}
