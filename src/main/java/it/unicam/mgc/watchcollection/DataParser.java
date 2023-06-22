package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class DataParser {
    public static ArrayList<LinkedHashMap<String, String>> parser(ArrayList<QuerySolution> querySolutions) {

        ArrayList<LinkedHashMap<String, String>> hashMapSet = new ArrayList<>();

        for (QuerySolution querySolution : querySolutions) {

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

            Iterator<String> varNames = querySolution.varNames();

            while (varNames.hasNext()) {
                String varName = varNames.next();

                hashMap.put(varName, querySolution.getLiteral(varName).getString());
            }

            hashMapSet.add(hashMap);
        }

        return hashMapSet;
    }
}
