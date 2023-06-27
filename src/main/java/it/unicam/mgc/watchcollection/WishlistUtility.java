package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WishlistUtility extends TabUtility {

    public ArrayList<LinkedHashMap<String, String>> get(String userEmail) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.GET_USER_WISHLIST.getQuery(), "?email", userEmail)));
    }

    public void add(String userEmail, String reference) {

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("?email", userEmail);
        parameter.put("?referenceString", reference);

        QueryExecutor.addDataQuery(this.infModel, QueryUtility.addFilters(WishlistQuery.ADD_WATCH_WISHLIST.getQuery(), parameter));
    }
    public void remove(String userEmail, String reference) {

    }
}