package it.unicam.mgc.watchcollection;

import it.unicam.mgc.watchcollection.model.utilities.QueryExecutor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class QueryExecutorTest {

    private Model model;

    @BeforeEach
    public void setup() {
        this.model = modelCreation();
    }

    @Test
    public void selectionQuery() {

        String query = "SELECT ?name WHERE { <http://example.org/resource/person> <http://example.org/property/name> ?name }";

        QueryExecutor queryExecutor = new QueryExecutor();

        ArrayList<QuerySolution> solutions = queryExecutor.selectionQuery(this.model, query);

        assertNotNull(model);
        assertEquals(2, solutions.size());
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
}