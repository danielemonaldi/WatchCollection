package it.unicam.mgc.watchcollection;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.update.*;

import java.io.*;
import java.util.ArrayList;

public class QueryExecutor {
    public static ArrayList<QuerySolution> selectDataQuery(Model model, String query) {

        ArrayList<QuerySolution> resultList = new ArrayList<>();

        Query sparqlQuery = QueryFactory.create(query);

        try (QueryExecution exec = QueryExecution.create(sparqlQuery, model)) {

            ResultSet results = exec.execSelect();

            while (results.hasNext()) {
                QuerySolution sol = results.nextSolution();
                resultList.add(sol);
            }
        }

        return resultList;
    }

    public static void addDataQuery(InfModel model, String query) {

        Dataset dataset = DatasetFactory.createTxnMem();
        dataset.setDefaultModel(model);

        UpdateRequest updateRequest = UpdateFactory.create(query);
        UpdateExecution updateExecution = null;
        updateExecution = UpdateExecutionFactory.create(updateRequest, dataset);
        updateExecution.execute();
    }
}
