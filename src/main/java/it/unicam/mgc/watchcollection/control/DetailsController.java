package it.unicam.mgc.watchcollection.control;

import it.unicam.mgc.watchcollection.model.Database;
import it.unicam.mgc.watchcollection.view.DetailsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class DetailsController {

    // FXML UI objects
    @FXML
    private Button returnButton;

    @FXML
    private HBox hbox;

    private final Database database = new Database();
    private final DetailsManager detailsManager = new DetailsManager();

    /**
     * Method used to initialize this controller. This method is invoked by JavaFX.
     */
    public void initialize() throws IOException {

        // Set return button image and event
        Image returnImage = new Image("images/return.png");
        returnButton.setGraphic(new ImageView(returnImage));
        returnButton.setOnAction(event -> closeCurrentWindow());
    }

    /**
     * Get the detailed information of a watch and set it on the UI.
     *
     * @param referenceString     Watch reference string.
     */
    public void setReference(String referenceString) {
        detailsManager.setData(database.getWatchDetails(referenceString).get(0), hbox);
    }

    /**
     *  Close the current window.
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
}
