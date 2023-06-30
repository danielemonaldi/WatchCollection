package it.unicam.mgc.watchcollection.model;

import it.unicam.mgc.watchcollection.model.utilities.DataParser;
import it.unicam.mgc.watchcollection.model.utilities.QueryExecutor;
import it.unicam.mgc.watchcollection.model.queries.CollectionQuery;
import it.unicam.mgc.watchcollection.model.utilities.QueryUtility;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Utility with methods for managing the user collection.
 * Each method uses a CollectionQuery SPARQL query.
 */
public class Collection extends TabUtility {

    DataParser dataParser = new DataParser();
    QueryExecutor queryExecutor = new QueryExecutor();

    /**
     * Get the watches in the user's collection.
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @return              List of HashMap. Each HashMap corresponds to a record of the query result, therefore to a watch in the collection.
     */
    public ArrayList<LinkedHashMap<String, String>> get(String userEmail) {

        return dataParser.parser(queryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.GET_USER_COLLECTION.getQuery(), "?email", userEmail)));
    }

    /**
     * Add a watch in the user's collection.
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @param reference     Reference string of the watch to add.
     */
    public void add(String userEmail, String reference) {

        // Creation of the list of parameters for the construction of the query
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("?email", userEmail);
        parameters.put("?referenceString", reference);

        // Execution of the parameterized query
        queryExecutor.updateQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.ADD_WATCH_COLLECTION.getQuery(), parameters));
    }

    /**
     * Remove a watch from a user's collection
     *
     * @param userEmail     Email used to identify the user and his collection.
     * @param reference     Reference string of the watch to remove.
     */
    public void remove(String userEmail, String reference) {

        // Creation of the list of parameters for the construction of the query
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("?email", userEmail);
        parameters.put("?referenceString", reference);

        // Execution of the parameterized query
        queryExecutor.updateQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.REMOVE_WATCH_COLLECTION.getQuery(), parameters));
    }
}
