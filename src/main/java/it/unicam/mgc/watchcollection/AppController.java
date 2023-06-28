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

    // FXML UI objects
    @FXML
    private TilePane databaseTilePane;

    @FXML
    private TilePane collectionTilePane;

    @FXML
    private TilePane wishlistTilePane;

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
    private MenuItem allOption;

    @FXML
    private MenuItem automaticOption;

    @FXML
    private MenuItem manualOption;

    @FXML
    private MenuItem quartzOption;

    /*
     * User email
     *
     * Example user, used to get the collection
     * and the wishlist in the application.
     */
    private final String userEmail = "user@gmail.com";

    // UI Tab utilities
    DatabaseUtility database = new DatabaseUtility();
    CollectionUtility collection = new CollectionUtility();
    WishlistUtility wishlist = new WishlistUtility();

    /**
     * Method used to initialize this controller. This method is invoked by JavaFX.
     */
    public void initialize() throws IOException {

        // UI settings
        Image searchImage = new Image("images/search.png");
        modelSearch.setGraphic(new ImageView(searchImage));
        referenceSearch.setGraphic(new ImageView(searchImage));

        setEvents();
        createDatabaseCards();
        createCollectionCards();
        createWishlistCards();
    }

    /**
     * Get the watches from the database and creates a
     * card for each one and adds it to the specified TilePane.
     *
     * @throws IOException
     */
    private void createDatabaseCards() throws IOException {
        createCards(database.get(), this.databaseTilePane);
    }

    /**
     * Get the watches from the user's collection and creates
     * a card for each one and adds it to the specified TilePane.
     *
     * @throws IOException
     */
    private void createCollectionCards() throws IOException {
        createCards(collection.get(this.userEmail), this.collectionTilePane);
    }

    /**
     * Get the watches from the user's wishlist and creates a
     * card for each one and adds it to the specified TilePane.
     *
     * @throws IOException
     */
    private void createWishlistCards() throws IOException {
        createCards(wishlist.get(this.userEmail), this.wishlistTilePane);
    }

    /**
     * Search for watches in the database by their model name.
     *
     * @throws IOException
     */
    private void modelSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();
        createCards(database.watchModelSearch(modelInput.getText()), this.databaseTilePane);
    }

    /**
     * Search for watch in the database by its reference string.
     *
     * @throws IOException
     */
    private void referenceSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();
        createCards(database.watchReferenceSearch(referenceInput.getText()), this.databaseTilePane);
    }

    /**
     * Create cards for each watches in the list.
     * The list contains the result of a query.
     *
     * @param hashMapSet        List of HashMap. Each HashMap is a record of the query result.
     * @param tilePane          Tile pane where to create the cards.
     *
     * @throws IOException
     */
    private void createCards(ArrayList<LinkedHashMap<String, String>> hashMapSet, TilePane tilePane) throws IOException {

        for (LinkedHashMap<String, String> hashMap : hashMapSet) {
            // Card creation
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox vBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            // Set the watch information in the card
            cardController.setData(hashMap);
            // Add card to TilePane
            tilePane.getChildren().add(vBox);
        }
    }


    /**
     * Sets events for objects on the UI
     */
    private void setEvents() {

        // Search by model name
        modelSearch.setOnAction(event -> {
            try {
                modelSearch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Search by reference string
        referenceSearch.setOnAction(event -> {
            try {
                referenceSearch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Movement type filter
        // All movement type
        allOption.setOnAction(event -> {
            movementFilter.setText("Movement type: All");
            this.databaseTilePane.getChildren().clear();
            try {
                createDatabaseCards();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Automatic winding movement type
        automaticOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Automatic winding");
            this.databaseTilePane.getChildren().clear();
            try {
                createCards(database.getWatchByMovementType("AutomaticWinding"), this.databaseTilePane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Manual winding movement type
        manualOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Manual winding");
            this.databaseTilePane.getChildren().clear();
            try {
                createCards(database.getWatchByMovementType("ManualWinding"), this.databaseTilePane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Quartz movement type
        quartzOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Quartz");
            this.databaseTilePane.getChildren().clear();
            try {
                createCards(database.getWatchByMovementType("QuartzMovement"), this.databaseTilePane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Reset all filters
        resetFilters.setOnAction(event -> {
            try {
                resetFilters();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * Reset all the filters in the UI.
     *
     * @throws IOException
     */
    private void resetFilters() throws IOException {
        modelInput.clear();
        referenceInput.clear();
        movementFilter.setText("Movement type: All");
        this.databaseTilePane.getChildren().clear();
        createDatabaseCards();
    }
}
