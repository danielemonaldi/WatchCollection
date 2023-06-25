package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WishlistUtility {

    private final InfModel infModel = InfModelUtility.create();
    public void addWatchWishlist(String userEmail, String reference) {

        LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("?email", userEmail);
        parameter.put("?referenceString", reference);

        //QueryExecutor.addDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.ADD_WATCH_WISHLIST.getQuery(), parameter));
        QueryExecutor.addDataQuery(this.infModel, OntologyQuery.TEST.getQuery());
    }

    public ArrayList<LinkedHashMap<String, String>> getUserWishlist(String userEmail) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_USER_WISHLIST.getQuery(), "?email", userEmail)));
    }
}