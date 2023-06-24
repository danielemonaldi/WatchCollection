package it.unicam.mgc.watchcollection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
    private TilePane wishlistTilePane;

    @FXML
    private Tab databaseTab;

    @FXML
    private Tab collectionTab;

    @FXML
    private Tab wishlistTab;

    @FXML
    private TextField modelInput;

    @FXML
    private TextField referenceInput;

    @FXML
    private Button modelSearch;

    @FXML
    private Button referenceSearch;


    private final String userEmail = "user@gmail.com";

    DatabaseUtility database = new DatabaseUtility();
    WishlistUtility wishlist = new WishlistUtility();

    public void initialize() throws IOException {
        modelSearch.setOnAction(event -> {
            try {
                modelSearch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        referenceSearch.setOnAction(event -> {
            try {
                referenceSearch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        createDatabaseCards();
        createWishlistCards();
    }
    private void createDatabaseCards() throws IOException {
        createCards(database.getAllWatches(), this.databaseTilePane);
    }

    private void createCollectionCards() throws IOException {
    }

    private void createWishlistCards() throws IOException {
        createCards(wishlist.getUserWishlist(this.userEmail), this.wishlistTilePane);
    }

    private void modelSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();

        if (modelInput.getText() != "") {
            createCards(database.watchModelSearch(modelInput.getText()), this.databaseTilePane);
        } else createCollectionCards();
    }

    private void referenceSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();

        if (referenceInput.getText() != "") {
            createCards(database.watchReferenceSearch(referenceInput.getText()), this.databaseTilePane);
        } else createCollectionCards();
    }

    private void createCards(ArrayList<LinkedHashMap<String, String>> hashMapSet, TilePane tilePane) throws IOException {

        for (LinkedHashMap<String, String> hashMap : hashMapSet) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox vBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(hashMap);
            tilePane.getChildren().add(vBox);
        }
    }

}
