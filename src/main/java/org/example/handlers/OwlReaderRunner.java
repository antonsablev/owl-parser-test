package org.example.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.example.model.customowlclass.CustomClassOwl;
import org.example.model.customowlindividual.CustomIndividualOwl;
import org.example.model.customowlproperty.CustomPropertyOwl;
import org.example.service.ClassOwlService;
import org.example.service.IndividualOwlService;
import org.example.service.PropertyOwlService;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import java.util.Set;

public class OwlParserRunner {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public void run(String uri) {
        OwlDownloadHandler downloadHandler = new OwlDownloadHandler();
        OWLOntology ontology = downloadHandler.getOntology(uri);

        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());

        ClassOwlService owlService = new ClassOwlService(ontology, reasoner);

        Set<CustomClassOwl> customEntities = owlService.createCustomEntities();
        customEntities.forEach(entity -> logger.info("Class: {}", entity));

        IndividualOwlService individualOwlService = new IndividualOwlService(ontology);
        Set<CustomIndividualOwl> listIndividuals = individualOwlService.getIndividuals();
        listIndividuals.forEach(entity -> logger.info("Individual: {}", entity));

        PropertyOwlService propertyOwlService = new PropertyOwlService(ontology);
        Set<CustomPropertyOwl> listProperties = propertyOwlService.getProperties();
        listProperties.forEach(entity -> logger.info("Properties: {}", entity));
    }
}
