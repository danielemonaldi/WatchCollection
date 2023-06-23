package it.unicam.mgc.watchcollection;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

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

    public static void addDataQuery(Model model, String query) {

        UpdateRequest updateRequest = UpdateFactory.create(query);
        UpdateAction.execute(updateRequest, model);
    }

}
