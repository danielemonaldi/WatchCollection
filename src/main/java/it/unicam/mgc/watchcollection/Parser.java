package it.unicam.mgc.watchcollection;

import org.apache.jena.query.QuerySolution;
import java.util.ArrayList;

/**
 * This interface defines a contract for a generic data parser.
 * It takes care of transforming the results of a query into a specific data structure.
 *
 * @param <T>       Data type or structure contained in the ArrayList.
 */

public interface Parser<T> {

    /**
     * Parse a QuerySolution into an ArrayList of data.
     *
     * @param querySolutions        SPARQL query solution.
     * @return                      Data structure containing the results of the query.
     */
    ArrayList<T> parser(ArrayList<QuerySolution> querySolutions);
}