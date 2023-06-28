package it.unicam.mgc.watchcollection;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import java.util.ArrayList;

public class QueryExecutor {


    /**
     * Performs a SELECT query on the ontology.
     *
     * @param model     Model on which perform the query.
     * @param query     Query to perform.
     * @return          QuerySolution.
     */
    public static ArrayList<QuerySolution> selectionQuery(Model model, String query) {

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

    /**
     * Performs an UPDATE query (INSERT or DELETE ) on the ontology.
     *
     * @param model     Model on which perform the query.
     * @param query     Query to perform.
     */
    public static void updateQuery(InfModel model, String query) {
    }
}
