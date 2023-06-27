package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CollectionUtility extends TabUtility {

    public ArrayList<LinkedHashMap<String, String>> getUserCollection(String userEmail) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(CollectionQuery.GET_USER_COLLECTION.getQuery(), "?email", userEmail)));
    }

    public void addWatchCollection(String userEmail, String reference) {

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("?email", userEmail);
        parameter.put("?referenceString", reference);

        QueryExecutor.addDataQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.ADD_WATCH_WISHLIST.getQuery(), parameter));
    }
}
