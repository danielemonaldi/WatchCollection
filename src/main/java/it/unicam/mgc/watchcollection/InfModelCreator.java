package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.reasoner.Reasoner;

public interface InfModelCreator {

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
