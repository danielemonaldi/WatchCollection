package it.unicam.mgc.watchcollection;

import java.util.Map;

public enum OntologyQuery {
    GET_ALL_WATCHES_BASIC("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?organization rdf:type foaf:Organization .\n" +
            "  ?organization foaf:name ?organizationName .\n" +
            "  ?organization foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName .\n" +
            "  ?model wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString .\n" +
            "  ?reference wa:imageLink ?watchImage .\n" +
            "}"),

    GET_WATCH_DETAILS("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString ?introductionYear ?listPrice ?waterResistance ?watchTypeName ?caseMaterialName ?strapMaterialName ?glassMaterialName\n" +
            "WHERE {\n" +
            "  ?organization rdf:type foaf:Organization .\n" +
            "  ?organization foaf:name ?organizationName .\n" +
            "  ?organization foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName .\n" +
            "  ?model wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString .\n" +
            "  ?reference wa:imageLink ?watchImage .\n" +
            "  ?reference wa:introductionYear ?introductionYear .\n" +
            "  ?reference wa:listPrice ?listPrice .\n" +
            "  ?reference wa:waterResistance ?waterResistance .\n" +
            "  ?reference wa:hasWatchType ?watchType .\n" +
            "  ?watchType wa:type ?watchTypeName .\n" +
            "  ?reference wa:hasCaseMaterial ?caseMaterial .\n" +
            "  ?reference wa:hasStrapMaterial ?strapMaterial .\n" +
            "  ?reference wa:hasGlassMaterial ?glassMaterial .\n" +
            "  ?caseMaterial foaf:name ?caseMaterialName .\n" +
            "  ?strapMaterial foaf:name ?strapMaterialName .\n" +
            "  ?glassMaterial foaf:name ?glassMaterialName .\n" +
            "}"),

    GET_WATCH_DIMENSION("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?diameter ?lugToLug ?thickness ?handle\n" +
            "WHERE {\n" +
            "  ?reference rdf:type wa:Reference .\n" +
            "  ?reference wa:reference ?referenceString .\n" +
            "  ?reference wa:hasDimension ?dimension .\n" +
            "  ?dimension wa:diameter ?diameter .\n" +
            "  ?dimension wa:lugToLug ?lugToLug .\n" +
            "  ?dimension wa:thickness ?thickness .\n" +
            "  ?dimension wa:handle ?handle .\n" +
            "}"),
    GET_WATCH_MOVEMENT("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchMaker ?movementName ?rotor ?reserve ?jewels ?frequency ?coscCertification ?batteryType ?movementImage ?complicationName\n" +
            "WHERE {\n" +
            "  ?reference rdf:type wa:Reference .\n" +
            "  ?reference wa:reference ?referenceString .\n" +
            "  ?reference wa:hasMovement ?movement .\n" +
            "  ?movement foaf:maker ?organization .\n" +
            "  ?organization foaf:name ?watchMaker .\n" +
            "  ?movement foaf:name ?movementName .\n" +
            "  ?movement wa:imageLink ?movementImage .\n" +
            "  ?movement wa:reserve ?reserve . \n" +
            "  ?movement wa:jewels ?jewels . \n" +
            "  ?movement wa:coscCertification ?coscCertification .\n" +
            "  ?movement wa:hasComplication ?complication .\n" +
            "  ?complication wa:complication ?complicationName .\n" +
            "  OPTIONAL { ?movement wa:rotor ?rotor . } \n" +
            "  OPTIONAL { ?movement wa:frequency ?frequency . } \n" +
            "  OPTIONAL { ?movement wa:batteryType ?batteryType . } \n" +
            "}"),

    GET_USER_WISHLIST("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "\n" +
            "SELECT ?watchImage ?organizationName ?modelName ?referenceString\n" +
            "WHERE {\n" +
            "  ?user rdf:type wa:User .\n" +
            "  ?user wa:email ?email .\n" +
            "  ?user wa:owns ?wishlist .\n" +
            "  ?wishlist rdf:type wa:Wishlist .\n" +
            "  ?wishlist wa:contains ?reference .\n" +
            "  ?organization rdf:type foaf:Organization .\n" +
            "  ?organization foaf:name ?organizationName .\n" +
            "  ?organization foaf:made ?model .\n" +
            "  ?model foaf:name ?modelName .\n" +
            "  ?model wa:hasReference ?reference .\n" +
            "  ?reference wa:reference ?referenceString .\n" +
            "  ?reference wa:imageLink ?watchImage .\n" +
            "}"),

    ADD_WATCH_WISHLIST("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX wa: <http://www.unicam.it/WatchCollection#>\n" +
            "INSERT DATA { \n" +
            "  wa:UserWishlist wa:contains wa:116500LN .\n" +
            "}");

    private final String query;

    private OntologyQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

