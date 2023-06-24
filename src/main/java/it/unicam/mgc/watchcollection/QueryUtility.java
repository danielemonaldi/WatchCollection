package it.unicam.mgc.watchcollection;

import java.util.Map;

public class QueryUtility {
    public static String addFilters(String query, Map<String, Object> filter) {

        StringBuilder sb = new StringBuilder(query.substring(0, query.length() - 1));

        for (Map.Entry<String, Object> entry : filter.entrySet()) {

            sb.append("  FILTER (").append(entry.getKey()).append(" = ").append(convertValueToString(entry.getValue())).append(").\n");
        }

        sb.append("}");

        return sb.toString();
    }

    public static String addFilters(String query, String parameter, Object value) {

        return query.substring(0, query.length() - 1) + "  FILTER (" + parameter + " = " + convertValueToString(value) + ").\n" + "}";
    }

    public static String addFiltersContains(String query, String parameter, Object value) {

        return query.substring(0, query.length() - 1) + "  FILTER (CONTAINS(lcase(" + parameter + "), lcase(" + convertValueToString(value) + "))).\n" + "}";
    }

    private static String convertValueToString(Object object) {
        if (object instanceof String) {
            return "\"" + object + "\"";
        }
        return object.toString();
    }
}
