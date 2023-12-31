package it.unicam.mgc.watchcollection.model.queries;

/**
 * SPARQL queries for managing user's collection.
 */
public enum CollectionQuery implements OntologyQuery {
    GET_USER_COLLECTION("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?user rdf:type wa:User ;\n" +
            "  wa:email ?email ;\n" +
            "  wa:owns ?collection .\n" +
            "  ?collection rdf:type wa:Collection ;\n" +
            "  wa:contains ?reference .\n" +
            "  ?organization rdf:type foaf:Organization ;\n" +
            "  foaf:name ?organizationName ;\n" +
            "  foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName ;\n" +
            "  wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString ;\n" +
            "  wa:imageLink ?watchImage .\n" +
            "}"),
    ADD_WATCH_COLLECTION(""),

    REMOVE_WATCH_COLLECTION("");

    private final String query;

    private CollectionQuery(String query) {
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }
}
