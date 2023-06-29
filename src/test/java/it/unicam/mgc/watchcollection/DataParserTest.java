package it.unicam.mgc.watchcollection;

import static org.junit.jupiter.api.Assertions.*;

import it.unicam.mgc.watchcollection.model.DataParser;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;

class DataParserTest {

    private Model model;

    @BeforeEach
    public void setup() {
        this.model = modelCreation();
    }

    @Test
    public final void testParser() {

        DataParser dataParser = new DataParser();
        dataParser.parser(queryExecutor(modelCreation()));

        ArrayList<LinkedHashMap<String, String>> dataList = dataParser.parser(queryExecutor(this.model));
        LinkedHashMap<String, String> data = dataList.get(0);

        assertEquals(2, dataList.size());
        assertNotNull(data.get("name"));
        assertNull(data.get("surname"));
        assertFalse(data.containsKey("age"));
    }

    /**
     * Creating a sample model with a statement.
     */
    public Model modelCreation() {

        model = ModelFactory.createDefaultModel();

        String subject1 = "http://example.org/resource/person";
        String predicate1 = "http://example.org/property/name";
        String objectValue1 = "John";

        String subject2 = "http://example.org/resource/person";
        String predicate2 = "http://example.org/property/name";
        String objectValue2 = "Mike";

        model.createResource(subject1).addProperty(model.createProperty(predicate1), objectValue1);
        model.createResource(subject2).addProperty(model.createProperty(predicate2), objectValue2);

        return model;
    }

    /**
     * Execution of an example query on the model.
     */
    public ArrayList<QuerySolution> queryExecutor(Model model) {

        String queryString = "SELECT ?name WHERE { <http://example.org/resource/person> <http://example.org/property/name> ?name }";

        ArrayList<QuerySolution> resultList = new ArrayList<>();

        Query sparqlQuery = QueryFactory.create(queryString);

        try (QueryExecution exec = QueryExecution.create(sparqlQuery, model)) {

            ResultSet results = exec.execSelect();

            while (results.hasNext()) {
                QuerySolution sol = results.nextSolution();
                resultList.add(sol);
            }
        }

        return resultList;
    }
}