package org.example.model.customowlindividual;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;

@Getter
@Setter
@AllArgsConstructor
public class CustomIndividualsAnnotation {
    private OWLAnnotationProperty property;
    private OWLAnnotationValue value;

    public String getPropertyFragment() {
        return property.getIRI().getFragment();
    }

    @Override
    public String toString() {
        return "\nIndividualsAnnotation{" +
                "property = " + getPropertyFragment() +
                ", value = " + value +
                "}";
    }
}
