package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CollectionUtility extends TabUtility {

    /*
     * Utility with methods for managing the user collection.
     * Each method uses a CollectionQuery SPARQL query.
     */

    /**
     * Get the references in the user's collection.
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @return              List of references in the collection.
     */
    public ArrayList<LinkedHashMap<String, String>> get(String userEmail) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.GET_USER_COLLECTION.getQuery(), "?email", userEmail)));
    }

    /**
     * Add a reference in the user's collection.
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @param reference     Reference string of the reference to add.
     */
    public void add(String userEmail, String reference) {

        // Creation of the list of parameters for the construction of the addition query
        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("?email", userEmail);
        parameter.put("?referenceString", reference);

        // Execution of the parameterized query
        QueryExecutor.addDataQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.ADD_WATCH_COLLECTION.getQuery(), parameter));
    }

    /**
     * Remove a reference from a user's collection
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @param reference     Reference string of the reference to remove.
     */
    public void remove(String userEmail, String reference) {
        // NO IMPLEMENTATION
    }
}
