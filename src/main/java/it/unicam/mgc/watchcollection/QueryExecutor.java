package it.unicam.mgc.watchcollection;

import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;

import java.util.ArrayList;

public class QueryExecutor {
    public static ArrayList<QuerySolution> selectQuery(Model model, String query) {

        ArrayList<QuerySolution> resultList = new ArrayList<>();

        Query sparql = QueryFactory.create(query);

        try (QueryExecution exec = QueryExecution.create(sparql, model)) {

            ResultSet results = exec.execSelect();

            while (results.hasNext()) {
                QuerySolution sol = results.nextSolution();
                resultList.add(sol);
            }
        }

        return resultList;
    }



}
