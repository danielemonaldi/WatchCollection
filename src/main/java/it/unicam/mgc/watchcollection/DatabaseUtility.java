package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DatabaseUtility {

    private final InfModel infModel = InfModelUtility.create();

    public ArrayList<LinkedHashMap<String, String>> getAllWatches() {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, OntologyQuery.GET_ALL_WATCHES_BASIC.getQuery()));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchDetails(String reference) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_DETAILS.getQuery(), "?referenceString", reference)));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchDimension(String reference) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_DIMENSION.getQuery(), "?referenceString", reference)));
    }

    public ArrayList<LinkedHashMap<String, String>> getWatchMovement(String reference) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_MOVEMENT.getQuery(), "?referenceString", reference)));
    }

    public ArrayList<LinkedHashMap<String, String>> getAutomaticWatch() {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_MOVEMENT.getQuery(), "?rotor", "true")));
    }

    public ArrayList<LinkedHashMap<String, String>> getMechanicalWatch() {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_MOVEMENT.getQuery(), "?rotor", "true")));
    }

    public ArrayList<LinkedHashMap<String, String>> getQuartzWatch() {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFilters(OntologyQuery.GET_WATCH_MOVEMENT.getQuery(), "?referenceString", "reference")));
    }

    public ArrayList<LinkedHashMap<String, String>> watchModelSearch(String modelName) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFiltersContains(OntologyQuery.GET_ALL_WATCHES_BASIC.getQuery(), "?modelName", modelName)));
    }

    public ArrayList<LinkedHashMap<String, String>> watchReferenceSearch(String modelName) {

        return DataParser.parser(QueryExecutor.selectDataQuery(this.infModel, QueryUtility.addFiltersContains(OntologyQuery.GET_ALL_WATCHES_BASIC.getQuery(), "?referenceString", modelName)));
    }
}
