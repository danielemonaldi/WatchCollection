package it.unicam.mgc.watchcollection.view;

import javafx.scene.Node;

/**
 * The interface defines a contract for a class that manages and sets data on objects of type Node.
 *
 * @param <M>       Data structure;
 * @param <T>       JavaFX Node object.
 */
public interface Manager<M, T extends Node> {

    /**
     * Sets the data contained in a data structure to a JavaFX element.
     *
     * @param data      Data structure.
     * @param node      JavaFX Node object.
     */
    void setData(M data, T node);
}
