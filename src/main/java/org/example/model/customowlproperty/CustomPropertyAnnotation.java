package org.example.model.customowlproperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;

@Getter
@Setter
@AllArgsConstructor
public class CustomPropertyAnnotation {
    private OWLAnnotationProperty property;
    private OWLAnnotationValue value;

    public String getProperty() {
        return property.getIRI().getFragment();
    }

    @Override
    public String toString() {
        return "\nCustomPropertyAnnotation{" +
                "property = " + getProperty() +
                ",\nvalue=" + value +
                "}";
    }
}
