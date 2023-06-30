package it.unicam.mgc.watchcollection.model.queries;

/**
 * SPARQL queries for managing the watch database.
 */
public enum DatabaseQuery implements OntologyQuery {

    GET_ALL_WATCHES_BASIC("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?organization rdf:type foaf:Organization ;\n" +
            "  foaf:name ?organizationName ;\n" +
            "  foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName ;\n" +
            "  wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString ;\n" +
            "  wa:imageLink ?watchImage .\n" +
            "}"),

    GET_WATCH_DETAILS("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString ?introductionYear ?listPrice ?waterResistance ?watchTypeName ?caseMaterialName ?strapMaterialName ?glassMaterialName " +
            "?diameter ?lugToLug ?thickness ?handle " +
            "?watchMaker ?movementName ?movementType ?rotor ?reserve ?jewels ?coscCertification ?complicationName ?movementImage ?frequency ?batteryType\n" +
            "WHERE {\n" +
            "  ?organization rdf:type foaf:Organization ;\n" +
            "  foaf:name ?organizationName ;\n" +
            "  foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName ;\n" +
            "  wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString ;\n" +
            "  wa:imageLink ?watchImage ;\n" +
            "  wa:introductionYear ?introductionYear ;\n" +
            "  wa:listPrice ?listPrice ;\n" +
            "  wa:waterResistance ?waterResistance ;\n" +
            "  wa:hasWatchType ?watchType ;\n" +
            "  wa:hasCaseMaterial ?caseMaterial ;\n" +
            "  wa:hasStrapMaterial ?strapMaterial ;\n" +
            "  wa:hasGlassMaterial ?glassMaterial ;\n" +
            "  wa:hasDimensions ?dimensions ;\n" +
            "  wa:hasMovement ?movement .\n" +
            "  ?watchType foaf:name ?watchTypeName .\n" +
            "  ?caseMaterial foaf:name ?caseMaterialName .\n" +
            "  ?strapMaterial foaf:name ?strapMaterialName .\n" +
            "  ?glassMaterial foaf:name ?glassMaterialName .\n" +
            "  ?dimensions wa:diameter ?diameter ;\n" +
            "  wa:lugToLug ?lugToLug ;\n" +
            "  wa:thickness ?thickness ;\n" +
            "  wa:handle ?handle .\n" +
            "  ?movement foaf:maker ?movementOrganization ;\n" +
            "  foaf:name ?movementName ;\n" +
            "  wa:imageLink ?movementImage ;\n" +
            "  wa:reserve ?reserve ;\n" +
            "  wa:jewels ?jewels ;\n" +
            "  wa:coscCertification ?coscCertification ;\n" +
            "  wa:hasComplication ?complication ;\n" +
            "  rdf:type ?class .\n" +
            "  ?complication foaf:name ?complicationName .\n" +
            "  ?movementOrganization foaf:name ?watchMaker .\n" +
            "  ?class rdfs:label ?movementType .\n" +
            "  OPTIONAL { ?movement wa:frequency ?frequency . } \n" +
            "  OPTIONAL { ?movement wa:batteryType ?batteryType . } \n" +
            "}"),

    GET_WATCH_BY_MOVEMENT_TYPE("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?organization rdf:type foaf:Organization ;\n" +
            "  foaf:name ?organizationName ;\n" +
            "  foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName ;\n" +
            "  wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString ;\n" +
            "  wa:imageLink ?watchImage ;\n" +
            "  wa:hasMovement ?movement .\n" +
            "  ?movement rdf:type ?class .\n" +
            "  ?class rdfs:label ?movementType .\n" +
            "}");

    private final String query;

    private DatabaseQuery(String query) {
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }
}

