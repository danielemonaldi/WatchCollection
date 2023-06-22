package it.unicam.mgc.watchcollection;

import openllet.jena.PelletReasonerFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.riot.RDFDataMgr;

public class InfModelUtility {

    public static InfModel create() {
        Model data = RDFDataMgr.loadModel("owl/WatchCollection.owl");

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        reasoner = reasoner.bindSchema(data);

        return ModelFactory.createInfModel(reasoner, data);
    }
}
