package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.riot.RDFDataMgr;

/**
 * Utility class that provides a method to build
 * an inference model using a specific reasoner.
 */
public class InfModelUtility implements InfModelCreator {

    /**
     * Creating a model from an OWL file and applying the reasoner.
     *
     * @param URI           OWL file path.
     * @param reasoner      Type of reasoner used for inference.
     * @return              InfModel with specified reasoner.
     */
    @Override
    public InfModel create(String URI, Reasoner reasoner) {
        Model data = RDFDataMgr.loadModel(URI);
        reasoner.bindSchema(data);
        return ModelFactory.createInfModel(reasoner, data);
    }
}
