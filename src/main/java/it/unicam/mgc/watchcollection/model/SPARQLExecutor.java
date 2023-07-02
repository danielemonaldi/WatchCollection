package it.unicam.mgc.watchcollection.model;

import org.apache.jena.rdf.model.Model;
import java.util.ArrayList;

/**
 * This interface defines a contract for classes that want to implement a SPARQL query executor.
 *
 * @param <T>   Data type or structure contained in the ArrayList of query result.
 */
public interface SPARQLExecutor<T> {

    /**
     * Performs a SELECT SPARQL query on the ontology.
     *
     * @param model     Model on which to perform the SPARQL query.
     * @param query     SPARQL query to perform.
     * @return          List of results of type T.
     */
    ArrayList<T> selectionQuery(Model model, String query);

    /**
     * Performs an UPDATE SPARQL query (INSERT or DELETE) on the ontology.
     *
     * @param model     Model on which to perform the SPARQL query.
     * @param query     SPARQL query to perform.
     */
    void updateQuery(Model model, String query);
}
