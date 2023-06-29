package it.unicam.mgc.watchcollection.model;

import org.apache.jena.rdf.model.Model;

import java.util.List;

public interface SPARQLExecutor<T> {

    /**
     * Performs a SELECT SPARQL query on the ontology.
     *
     * @param model     Model on which to perform the SPARQL query.
     * @param query     SPARQL query to perform.
     * @return          List of results of type T.
     */
    List<T> selectionQuery(Model model, String query);

    /**
     * Performs an UPDATE SPARQL query (INSERT or DELETE) on the ontology.
     *
     * @param model     Model on which to perform the SPARQL query.
     * @param query     SPARQL query to perform.
     */
    void updateQuery(Model model, String query);
}
