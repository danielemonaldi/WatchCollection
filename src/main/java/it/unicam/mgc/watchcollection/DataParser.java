package it.unicam.mgc.watchcollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.apache.jena.query.QuerySolution;

public class DataParser {


    /**
     * Transform the result of a query into a data structure.
     * The data structure is a List of LinkedHashMaps, where
     * each LinkedHashMap corresponds to a record of the result
     * of a query.
     *
     * The key of each element of the hashmap is the name of a query
     * variable, while the value of each element is the value of the
     * respective variable,
     *
     * @param querySolutions        Solution of the query.
     * @return                      List of LinkedHashMap.
     */
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
