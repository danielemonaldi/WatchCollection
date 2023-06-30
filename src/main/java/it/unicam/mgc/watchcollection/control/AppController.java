package it.unicam.mgc.watchcollection.control;

import it.unicam.mgc.watchcollection.model.Collection;
import it.unicam.mgc.watchcollection.model.Database;
import it.unicam.mgc.watchcollection.model.Wishlist;
import it.unicam.mgc.watchcollection.view.CardManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;

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
    Database database = new Database();
    Collection collection = new Collection();
    Wishlist wishlist = new Wishlist();

    CardManager cardManager = new CardManager();

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
     * @throws IOException      File loader exception.
     */
    private void createDatabaseCards() throws IOException {
        cardManager.createCards(database.get(), this.databaseTilePane);
    }

    /**
     * Get the watches from the user's collection and creates
     * a card for each one and adds it to the specified TilePane.
     *
     * @throws IOException      File loader exception.
     */
    private void createCollectionCards() throws IOException {
        cardManager.createCards(collection.get(this.userEmail), this.collectionTilePane);
    }

    /**
     * Get the watches from the user's wishlist and creates a
     * card for each one and adds it to the specified TilePane.
     *
     * @throws IOException      File loader exception.
     */
    private void createWishlistCards() throws IOException {
        cardManager.createCards(wishlist.get(this.userEmail), this.wishlistTilePane);
    }

    /**
     * Search for watches in the database by their model name.
     *
     * @throws IOException      File loader exception.
     */
    private void modelSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();
        cardManager.createCards(database.watchModelSearch(modelInput.getText()), this.databaseTilePane);
    }

    /**
     * Search for watch in the database by its reference string.
     *
     * @throws IOException      File loader exception.
     */
    private void referenceSearch() throws IOException {
        this.databaseTilePane.getChildren().clear();
        cardManager.createCards(database.watchReferenceSearch(referenceInput.getText()), this.databaseTilePane);
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
                cardManager.createCards(database.getWatchByMovementType("Automatic Winding"), this.databaseTilePane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Manual winding movement type
        manualOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Manual winding");
            this.databaseTilePane.getChildren().clear();
            try {
                cardManager.createCards(database.getWatchByMovementType("Manual Winding"), this.databaseTilePane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Quartz movement type
        quartzOption.setOnAction(event -> {
            movementFilter.setText("Movement type: Quartz");
            this.databaseTilePane.getChildren().clear();
            try {
                cardManager.createCards(database.getWatchByMovementType("Quartz"), this.databaseTilePane);
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
     * @throws IOException      File loader exception.
     */
    private void resetFilters() throws IOException {
        modelInput.clear();
        referenceInput.clear();
        movementFilter.setText("Movement type: All");
        this.databaseTilePane.getChildren().clear();
        createDatabaseCards();
    }
}
