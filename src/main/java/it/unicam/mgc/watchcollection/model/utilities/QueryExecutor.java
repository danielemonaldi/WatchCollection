package it.unicam.mgc.watchcollection.model.utilities;

import it.unicam.mgc.watchcollection.model.SPARQLExecutor;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import java.util.ArrayList;

public class QueryExecutor implements SPARQLExecutor<QuerySolution> {

    /**
     * Performs a SELECT SPARQL query on the ontology.
     *
     * @param model     Model on which perform the SPARQL query.
     * @param query     SPARQL Query to perform.
     * @return          QuerySolution.
     */
    @Override
    public ArrayList<QuerySolution> selectionQuery(Model model, String query) {

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
     * Performs an UPDATE SPARQL query (INSERT or DELETE) on the ontology.
     *
     * @param model     Model on which perform the SPARQL query.
     * @param query     SPARQL Query to perform.
     */
    @Override
    public void updateQuery(Model model, String query) {
        throw new UnsupportedOperationException("This method is not implemented");
    }
}
