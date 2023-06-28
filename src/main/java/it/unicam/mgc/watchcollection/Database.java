package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Utility with methods for managing the watch database.
 * Each method uses a CollectionQuery SPARQL query.
 */

public class Database extends TabUtility {

    DataParser dataParser = new DataParser();

    /**
     * Get all watches of the ontology.
     *
     * @return      List of LinkedHashMap. Each LinkedHashMap corresponds to a record of the query result, therefore to a watch.
     */
    public ArrayList<LinkedHashMap<String, String>> get() {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, DatabaseQuery.GET_ALL_WATCHES_BASIC.getQuery()));
    }

    /**
     * Get the detailed information of a specific watch.
     *
     * @param reference     Reference string of the watch for which to get detailed information.
     * @return              List containing a LinkedHashMap. The LinkedHashMap contains the detailed information of a watch.
     */
    public ArrayList<LinkedHashMap<String, String>> getWatchDetails(String reference) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(DatabaseQuery.GET_WATCH_DETAILS.getQuery(), "?referenceString", reference)));
    }

    /**
     * Get the dimensions of a specific watch.
     *
     * @param reference     Reference string of the watch whose dimensions are to be obtained.
     * @return              List containing a LinkedHashMap. The LinkedHashMap contains the dimensions of a watch.
     */
    public ArrayList<LinkedHashMap<String, String>> getWatchDimensions(String reference) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(DatabaseQuery.GET_WATCH_DIMENSION.getQuery(), "?referenceString", reference)));
    }

    /**
     * Get the movement information of a specific watch.
     *
     * @param reference     Reference string of a watch whose movement information are to be obtained.
     * @return              List containing a LinkedHashMap. The LinkedHashMap contains the movement information.
     */
    public ArrayList<LinkedHashMap<String, String>> getWatchMovement(String reference) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(DatabaseQuery.GET_WATCH_MOVEMENT.getQuery(), "?referenceString", reference)));
    }


    /**
     * Search in the ontology all the watches of the same model.
     *
     * @param modelName     Name of the model to search.
     * @return              List of LinkedHashMap. Each LinkedHashMap corresponds to a watch whose model name matches "modelName".
     */
    public ArrayList<LinkedHashMap<String, String>> watchModelSearch(String modelName) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFiltersContains(DatabaseQuery.GET_ALL_WATCHES_BASIC.getQuery(), "?modelName", modelName)));
    }

    /**
     * Search in the ontology a watch based on its reference string.
     *
     * @param referenceString   Reference string to search.
     * @return                  List containing an LinkedHashMap. The LinkedHashMap contains the watch with that string reference, if it exists.
     */
    public ArrayList<LinkedHashMap<String, String>> watchReferenceSearch(String referenceString) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFiltersContains(DatabaseQuery.GET_ALL_WATCHES_BASIC.getQuery(), "?referenceString", referenceString)));
    }


    /**
     * Get all watches that have a specific type of movement. The movementType can be:
     * - AutomaticWinding: watches with automatic winding movement
     * - ManualWinding:    watches with manual winding movement
     * - Quartz:           watches with quartz movement
     *
     * @param movementType      Movement type.
     * @return                  List of LinkedHashMap. Each LinkedHashMap corresponds to a watch with "movementType" movement type.
     */
    public ArrayList<LinkedHashMap<String, String>> getWatchByMovementType(String movementType) {

        return dataParser.parser(QueryExecutor.selectionQuery(this.infModel, QueryUtility.addFilters(DatabaseQuery.GET_WATCH_BY_MOVEMENT_TYPE.getQuery(), "?movementType", movementType)));
    }
}
