package it.unicam.mgc.watchcollection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private Button resetFilters;

    @FXML
    private MenuButton movementFilter;

    @FXML
    private MenuItem automaticOption;

    @FXML
    private MenuItem mechanicalOption;

    @FXML
    private MenuItem quartzOption;

    private final String userEmail = "user@gmail.com";

    DatabaseUtility database = new DatabaseUtility();
    WishlistUtility wishlist = new WishlistUtility();

    public void initialize() throws IOException {

        Image searchImage = new Image("images/search.png");
        modelSearch.setGraphic(new ImageView(searchImage));
        referenceSearch.setGraphic(new ImageView(searchImage));

        setEvents();
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
        createCards(database.watchModelSearch(modelInput.getText()), this.databaseTilePane);
    }

    private void referenceSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();
        createCards(database.watchReferenceSearch(referenceInput.getText()), this.databaseTilePane);
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

    private void setEvents() {

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

        resetFilters.setOnAction(event -> {
            try {
                resetFilters();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        automaticOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Automatic winding");
        });

        mechanicalOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Mechanical winding");
        });

        quartzOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Quartz");
        });
    }

    private void resetFilters() throws IOException {
        modelInput.clear();
        referenceInput.clear();
        this.databaseTilePane.getChildren().clear();
        createDatabaseCards();
    }

}
