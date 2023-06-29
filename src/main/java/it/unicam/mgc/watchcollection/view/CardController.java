package it.unicam.mgc.watchcollection.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedHashMap;

public class CardController {

    // FXML UI objects
    @FXML
    private ImageView watchImage;

    @FXML
    private Text organizationName;

    @FXML
    private Text modelName;

    @FXML
    private Text referenceString;

    @FXML
    private Hyperlink details;

    /**
     * Method used to initialize this controller. This method is invoked by JavaFX.
     */
    public void initialize() {
        details.setOnAction(event -> {
            openDetailScene();
        });
    }

    /**
     * Create and open the screen to view the
     * detailed information of a specific watch.
     */
    private void openDetailScene() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/details.fxml"));
            Parent root = fxmlLoader.load();
            DetailsController detailsController = fxmlLoader.getController();

            // Get the reference whose detailed information are to be displayed
            detailsController.setData(referenceString.getText());

            // Hide the current screen
            Stage currentStage = (Stage) referenceString.getScene().getWindow();
            currentStage.hide();

            // UI settings
            Stage detailsStage = new Stage();
            detailsStage.setScene(new Scene(root));
            detailsStage.getIcons().add(new Image("images/icon.png"));
            detailsStage.setTitle("Watch Collection - " + organizationName.getText() + " " + modelName.getText() + " " + referenceString.getText());
            detailsStage.setResizable(false);

            // Open the new screen without closing the previous one
            detailsStage.setOnHidden(event -> currentStage.show());
            detailsStage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}