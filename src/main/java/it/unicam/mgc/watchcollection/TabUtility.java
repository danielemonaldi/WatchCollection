package it.unicam.mgc.watchcollection;

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
        InfModelUtility infModelUtility = new InfModelUtility();
        this.infModel = infModelUtility.create("owl/WatchCollection.owl", PelletReasonerFactory.THE_SPEC.getReasoner());
    }
}
