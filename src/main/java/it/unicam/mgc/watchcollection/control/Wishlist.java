package it.unicam.mgc.watchcollection.control;

import it.unicam.mgc.watchcollection.model.DataParser;
import it.unicam.mgc.watchcollection.model.QueryExecutor;
import it.unicam.mgc.watchcollection.model.TabUtility;
import it.unicam.mgc.watchcollection.model.queries.WishlistQuery;
import it.unicam.mgc.watchcollection.model.utilities.QueryUtility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Utility with methods for managing the user wishlist.
 * Each method uses a Wishlist SPARQL query.
 */
public class Wishlist extends TabUtility {

    DataParser dataParser = new DataParser();
    QueryExecutor queryExecutor = new QueryExecutor();

    /**
     * Get the watches in the user's wishlist.
     *
     * @param userEmail     Email used to identify the user and his wishlist.
     * @return              List of HashMap. Each HashMap corresponds to a record of the query result, therefore to a watch in the wishlist.
     */
    public ArrayList<LinkedHashMap<String, String>> get(String userEmail) {

        return dataParser.parser(queryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.GET_USER_WISHLIST.getQuery(), "?email", userEmail)));
    }

    /**
     * Add a watch in the user's wishlist.
     *
     * @param userEmail     Email used to identify the user and his wishlist.
     * @param reference     Reference string of the watch to add.
     */
    public void add(String userEmail, String reference) {

        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("?email", userEmail);
        parameters.put("?referenceString", reference);

        queryExecutor.updateQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.ADD_WATCH_WISHLIST.getQuery(), parameters));
    }

    /**
     * Remove a watch from a user's wishlist
     *
     * @param userEmail     Email used to identify the user and his wishlist.
     * @param reference     Reference string of the watch to remove.
     */
    public void remove(String userEmail, String reference) {

        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("?email", userEmail);
        parameters.put("?referenceString", reference);

        queryExecutor.updateQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.REMOVE_WATCH_WISHLIST.getQuery(), parameters));

    }
}