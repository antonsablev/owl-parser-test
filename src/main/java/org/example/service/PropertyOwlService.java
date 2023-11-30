package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.customowlproperty.CustomPropertyAnnotation;
import org.example.model.customowlproperty.CustomPropertyOwl;
import org.semanticweb.owlapi.model.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class PropertyOwlService {
    private final OWLOntology ontology;

    public Set<CustomPropertyOwl> getProperties() {
        Set<CustomPropertyOwl> props = new HashSet<>();
        Set<OWLObjectProperty> objectProperties = ontology.getObjectPropertiesInSignature();
        for (OWLObjectProperty objectProperty : objectProperties) {
            CustomPropertyOwl propertyOwl = createObjectProperty(objectProperty);
            props.add(propertyOwl);
        }
        return props;
    }

    private CustomPropertyOwl createObjectProperty(OWLObjectProperty objectProperty) {
        Set<OWLAnnotationAssertionAxiom> annotationAxioms = ontology.getAnnotationAssertionAxioms(objectProperty.getIRI());
        Set<CustomPropertyAnnotation> annotations = new HashSet<>();

        for (OWLAnnotationAssertionAxiom annotationAxiom : annotationAxioms) {
            OWLAnnotationProperty property = annotationAxiom.getProperty();
            OWLAnnotationValue value = annotationAxiom.getValue();

            CustomPropertyAnnotation customAnnotation = new CustomPropertyAnnotation(property, value);
            annotations.add(customAnnotation);
        }
        return new CustomPropertyOwl(objectProperty.getIRI(), annotations);
    }

}
