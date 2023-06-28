package it.unicam.mgc.watchcollection;

public enum WishlistQuery implements OntologyQuery {

    /**
     * SPARQL queries for managing user's wishlist.
     */

    GET_USER_WISHLIST("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?user rdf:type wa:User ;\n" +
            "  wa:email ?email ;\n" +
            "  wa:owns ?wishlist .\n" +
            "  ?wishlist rdf:type wa:Wishlist ;\n" +
            "  wa:contains ?reference .\n" +
            "  ?organization rdf:type foaf:Organization ;\n" +
            "  foaf:name ?organizationName ;\n" +
            "  foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName ;\n" +
            "  wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString ;\n" +
            "  wa:imageLink ?watchImage .\n" +
            "}"),
    ADD_WATCH_WISHLIST(""),

    REMOVE_WATCH_WISHLIST("");

    private final String query;

    private WishlistQuery(String query) {
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }
}
