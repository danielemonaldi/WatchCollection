package it.unicam.mgc.watchcollection.model.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import it.unicam.mgc.watchcollection.model.Parser;
import org.apache.jena.query.QuerySolution;

public class DataParser implements Parser<LinkedHashMap<String, String>, QuerySolution> {

    /**
     * Parse the result of a query into a data structure.
     * The data structure is a List of LinkedHashMaps, where
     * each LinkedHashMap corresponds to a record of the result
     * of a query.
     * <p>
     * The key of each element of the hashmap is the name of a query
     * variable, while the value of each element is the value of the
     * respective variable,
     *
     * @param querySolutions        Solution of the query.
     * @return                      List of LinkedHashMap containing the query result data.
     */
    @Override
    public ArrayList<LinkedHashMap<String, String>> parser(ArrayList<QuerySolution> querySolutions) {

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
