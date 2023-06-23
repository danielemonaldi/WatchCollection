package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Database {

    private final InfModel infModel = InfModelUtility.create();

    public ArrayList<LinkedHashMap<String, String>> getAllWatches() {

        return DataParser.parser(QueryExecutor.selectQuery(this.infModel, OntologyQuery.GET_ALL_WATCHES_BASIC.getQuery()));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchDetails(String reference) {

        return DataParser.parser(QueryExecutor.selectQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_DETAILS.getQuery(), "?referenceString", reference)));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchDimension(String reference) {

        return DataParser.parser(QueryExecutor.selectQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_DIMENSION.getQuery(), "?referenceString", reference)));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchMovement(String reference) {

        return DataParser.parser(QueryExecutor.selectQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_MOVEMENT.getQuery(), "?referenceString", reference)));
    }

    public void test(String user, String reference) {

    }
}
