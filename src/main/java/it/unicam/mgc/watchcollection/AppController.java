package it.unicam.mgc.watchcollection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AppController {

    @FXML
    private TilePane databaseTilePane;

    @FXML
    private TilePane collectionTilePane;

    @FXML
    private Tab databaseTab;

    @FXML
    private Tab collectionTab;

    @FXML
    private Tab wishlistTab;

    Database database = new Database();

    public void initialize() throws IOException {
        createDatabaseCards();
    }

    private void createDatabaseCards() throws IOException {

        ArrayList<LinkedHashMap<String, String>> hashMapSet = database.getAllWatches();

        for (LinkedHashMap<String, String> hashMap : hashMapSet) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox vBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(hashMap);
            databaseTilePane.getChildren().add(vBox);
        }

    }

    private void createCollectionCards() throws IOException {

        ArrayList<LinkedHashMap<String, String>> hashMapSet = database.getAllWatches();

        for (LinkedHashMap<String, String> hashMap : hashMapSet) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox vBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(hashMap);
            collectionTilePane.getChildren().add(vBox);
        }

    }

    private void createWishlistCards() throws IOException {

        ArrayList<LinkedHashMap<String, String>> hashMapSet = database.getAllWatches();

        for (LinkedHashMap<String, String> hashMap : hashMapSet) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox vBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(hashMap);
            collectionTilePane.getChildren().add(vBox);
        }

    }
}
