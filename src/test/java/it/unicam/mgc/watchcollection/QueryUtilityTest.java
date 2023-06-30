package it.unicam.mgc.watchcollection;

import it.unicam.mgc.watchcollection.model.utilities.QueryUtility;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class QueryUtilityTest {
    @Test
    void addFilters() {

        String query = "SELECT ?name ?age WHERE {\n  ?person foaf:name ?name.\n  ?person foaf:age ?age.\n}";
        LinkedHashMap<String, Object> filters = new LinkedHashMap<>();
        filters.put("?age", 30);
        filters.put("?name", "John");

        String result = QueryUtility.addFilters(query, filters);

        String expected = "SELECT ?name ?age WHERE {\n  ?person foaf:name ?name.\n  ?person foaf:age ?age.\n" +
                "  FILTER (?age = 30).\n  FILTER (?name = \"John\").\n}";

        assertEquals(expected, result);
    }

    @Test
    void testAddFilters() {

        String query = "SELECT ?name ?age WHERE {\n  ?person foaf:name ?name.\n  ?person foaf:age ?age.\n}";
        String parameter = "?age";
        Object value = 30;

        String result = QueryUtility.addFilters(query, parameter, value);

        String expected = "SELECT ?name ?age WHERE {\n  ?person foaf:name ?name.\n  ?person foaf:age ?age.\n" +
                "  FILTER (?age = 30).\n}";

        assertEquals(expected, result);
    }

    @Test
    void addFiltersContains() {
        String query = "SELECT ?name WHERE {\n  ?person foaf:name ?name.\n}";
        String parameter = "?name";
        Object value = "John";

        String result = QueryUtility.addFiltersContains(query, parameter, value);

        String expected = "SELECT ?name WHERE {\n  ?person foaf:name ?name.\n" +
                "  FILTER (CONTAINS(lcase(?name), lcase(\"John\"))).\n}";

        assertEquals(expected, result);
    }
}