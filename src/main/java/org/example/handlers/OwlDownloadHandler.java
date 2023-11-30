package org.example.handlers;

import org.example.exception.FailToLoadOntologyException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.net.URI;
import java.net.URISyntaxException;

public class OwlDownloadHandler {
    public OWLOntology getOntology(String link) {
        URI uri = null;
        try {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            uri = new URI(link);
            IRI iri = IRI.create(uri);
            return manager.loadOntologyFromOntologyDocument(iri);
        } catch (OWLOntologyCreationException | URISyntaxException e) {
            throw new FailToLoadOntologyException("Failed to load ontology from URI: " + uri);
        }
    }
}
