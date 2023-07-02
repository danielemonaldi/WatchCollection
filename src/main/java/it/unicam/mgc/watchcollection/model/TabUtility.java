package it.unicam.mgc.watchcollection.model;

import it.unicam.mgc.watchcollection.model.utilities.InfModelUtility;
import openllet.jena.PelletReasonerFactory;
import org.apache.jena.rdf.model.InfModel;

/**
 * Provides a common base for the classes representing the Tab,
 * allowing them to share the same inference model and simplifying
 * the process of building the inference model within derived classes.
 */
public abstract class TabUtility {

    protected InfModel infModel;

    public TabUtility() {
        // Creating the inference model and applying the Pellet reasoner
        InfModelUtility infModelUtility = new InfModelUtility();
        this.infModel = infModelUtility.create("owl/WatchCollection.owl", PelletReasonerFactory.THE_SPEC.getReasoner());
    }
}
