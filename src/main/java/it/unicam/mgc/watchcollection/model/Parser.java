package it.unicam.mgc.watchcollection.model;

import java.util.ArrayList;

/**
 * This interface defines a contract for a generic data parser.
 * It takes care of transforming the results of a query into a specific data structure.
 *
 * @param <T>       Data type or structure contained in the ArrayList of parsed data.
 * @param <M>       Data type or structure contained in the ArrayList of query result.
 */
public interface Parser<T, M> {

    /**
     * Parse a query result into an ArrayList of data.
     *
     * @param queryResult           SPARQL query result.
     * @return                      Data structure containing the results of the query.
     */
    ArrayList<T> parser(ArrayList<M> queryResult);
}