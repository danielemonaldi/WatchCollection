package it.unicam.mgc.watchcollection.model.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class QueryUtility {

    /**
     * Adds a set of FILTER to a SPARQL query.
     *
     * @param query     SPARQL query.
     * @param filter    Map of filters.
     * @return          SPARQL query with FILTER applied.
     */
    public static String addFilters(String query, LinkedHashMap<String, Object> filter) {

        // Remove the closing bracket of the query
        StringBuilder sb = new StringBuilder(query.substring(0, query.length() - 1));

        // Add FILTER
        for (Map.Entry<String, Object> entry : filter.entrySet()) {

            sb.append("  FILTER (").append(entry.getKey()).append(" = ").append(convertValueToString(entry.getValue())).append(").\n");
        }

        sb.append("}");

        return sb.toString();
    }

    /**
     * Add a single FILTER to a SPARQL query
     *
     * @param query         SPARQL query.
     * @param parameter     Filter variable.
     * @param value         Filter Variable value.
     * @return              SPARQL query with FILTER applied.
     */
    public static String addFilters(String query, String parameter, Object value) {

        return query.substring(0, query.length() - 1) + "  FILTER (" + parameter + " = " + convertValueToString(value) + ").\n" + "}";
    }

    /**
     * Adds a "CONTAINS" filter to the specified SPARQL query.
     * The CONTAINS operator is used to perform a case-insensitive search within the value of a parameter.
     *
     * @param query         SPARQL query.
     * @param parameter     Filter variable.
     * @param value         Filter Variable value.
     * @return              SPARQL query with FILTER applied.
     */
    public static String addFiltersContains(String query, String parameter, Object value) {

        return query.substring(0, query.length() - 1) + "  FILTER (CONTAINS(lcase(" + parameter + "), lcase(" + convertValueToString(value) + "))).\n" + "}";
    }

    /**
     * Converts a given object value to its string representation.
     * If the object is of type String, it is enclosed in double quotes.
     * Otherwise, the object's toString() method is used to obtain its string representation.
     *
     * @param object    The object value to convert.
     * @return          The string representation of the object value.
     */
    private static String convertValueToString(Object object) {
        if (object instanceof String) {
            return "\"" + object + "\"";
        }
        return object.toString();
    }
}
