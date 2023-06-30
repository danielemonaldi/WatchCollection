package it.unicam.mgc.watchcollection.view;

import javafx.scene.Node;
import java.util.LinkedHashMap;

/**
 * The interface defines a contract for a class that manages and sets data on objects of type Node.
 *
 * @param <T>       Node type object.
 */
public interface Manager<T extends Node> {
    void setData(LinkedHashMap<String, String> data, T node);
}
