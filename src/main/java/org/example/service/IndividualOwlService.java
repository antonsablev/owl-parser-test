package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.customowlindividual.CustomIndividualsAnnotation;
import org.example.model.customowlindividual.CustomIndividualOwl;
import org.semanticweb.owlapi.model.*;
import java.util.HashSet;
import java.util.Set;
@RequiredArgsConstructor
public class IndividualOwlService {
    private final OWLOntology ontology;

    public Set<CustomIndividualOwl> getIndividuals(){
        Set<CustomIndividualOwl> customIndividuals = new HashSet<>();
            Set<OWLNamedIndividual> individuals = ontology.getIndividualsInSignature();
            for (OWLNamedIndividual individual : individuals) {
                CustomIndividualOwl customIndividual = createCustomIndividual(individual);
                customIndividuals.add(customIndividual);
            }
        return customIndividuals;
    }

    private CustomIndividualOwl createCustomIndividual(OWLNamedIndividual individual) {
        Set<OWLAnnotationAssertionAxiom> annotationAxioms = ontology.getAnnotationAssertionAxioms(individual.getIRI());
        Set<CustomIndividualsAnnotation> annotations = new HashSet<>();

        for (OWLAnnotationAssertionAxiom annotationAxiom : annotationAxioms) {
            OWLAnnotationProperty property = annotationAxiom.getProperty();
            OWLAnnotationValue value = annotationAxiom.getValue();

            CustomIndividualsAnnotation customAnnotation = new CustomIndividualsAnnotation(property, value);
            annotations.add(customAnnotation);
        }
        return new CustomIndividualOwl(individual.getIRI(), annotations);
    }
}
