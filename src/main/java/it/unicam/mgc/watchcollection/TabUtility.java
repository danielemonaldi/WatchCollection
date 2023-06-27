package it.unicam.mgc.watchcollection;

import org.apache.jena.rdf.model.InfModel;
public abstract class TabUtility {

    protected InfModel infModel;

    public TabUtility() {
        this.infModel = InfModelUtility.create();
    }
}
