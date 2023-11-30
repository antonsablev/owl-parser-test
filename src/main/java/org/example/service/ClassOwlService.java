package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.customowlclass.CustomClassAnnotation;
import org.example.model.customowlclass.CustomClassOwl;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ClassOwlService {
    private final OWLOntology ontology;
    private final OWLReasoner reasoner;

    public Set<CustomClassOwl> createCustomEntities() {
        Set<CustomClassOwl> customEntities = new HashSet<>();
        for (OWLClass owlClass : ontology.getClassesInSignature()) {
            CustomClassOwl customEntity = createCustomEntity(owlClass);
            customEntities.add(customEntity);
        }
        return customEntities;
    }
    private CustomClassOwl createCustomEntity(OWLClass owlClass) {
        IRI iri = owlClass.getIRI();
        Set<CustomClassAnnotation> annotations = createCustomAnnotations(owlClass);
        Set<CustomClassOwl> parents = createCustomParents(owlClass);
        return new CustomClassOwl(iri, annotations, parents);
    }
    private Set<CustomClassAnnotation> createCustomAnnotations(OWLClass owlClass) {
        Set<CustomClassAnnotation> customAnnotations = new HashSet<>();
        for (OWLAnnotationAssertionAxiom annotationAxiom : ontology.getAnnotationAssertionAxioms(owlClass.getIRI())) {
            OWLAnnotationProperty annotationProperty = annotationAxiom.getProperty();
            OWLAnnotationValue value = annotationAxiom.getValue();
            customAnnotations.add(new CustomClassAnnotation(annotationProperty, value));
        }
        return customAnnotations;
    }

    private Set<CustomClassOwl> createCustomParents(OWLClass owlClass) {
        Set<CustomClassOwl> parents = new HashSet<>();
        Set<OWLClass> superClasses = reasoner.getSuperClasses(owlClass, true).getFlattened();
        for (OWLClassExpression superClass : superClasses) {
            if (!superClass.isAnonymous()) {
                CustomClassOwl parentEntity = createCustomEntity(superClass.asOWLClass());
                parents.add(parentEntity);
            }
        }
        return parents;
    }
}
