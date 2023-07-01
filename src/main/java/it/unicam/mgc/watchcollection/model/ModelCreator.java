package it.unicam.mgc.watchcollection.model;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.reasoner.Reasoner;

/**
 * Defines the contract for building an inference model
 * from an OWL file and applying a reasoner.
 */
public interface ModelCreator {

    /**
     * Creates an InfModel from an OWL file located at the
     * specified URI and applies the specified reasoner.
     *
     * @param URI           URI of the OWL file.
     * @param reasoner      Reasoner to be applied to the model.
     * @return              InfModel with the applied reasoner.
     */
    InfModel create(String URI, Reasoner reasoner);
}
