package it.unicam.mgc.watchcollection;

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
    private Text reference;

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
     * Set the information of the watch in the card.
     *
     * @param data      HashMap containing the information of a watch.
     */
    public void setData(LinkedHashMap<String, String> data) {
        Image waImage = new Image(data.get("watchImage"));
        watchImage.setImage(waImage);
        organizationName.setText(data.get("organizationName"));
        modelName.setText(data.get("modelName"));
        reference.setText(data.get("referenceString"));
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

            // Get the reference whose detailed information is to be displayed
            detailsController.setReference(reference.getText());

            // Hide the current screen
            Stage currentStage = (Stage) reference.getScene().getWindow();
            currentStage.hide();

            // UI settings
            Stage detailsStage = new Stage();
            detailsStage.setScene(new Scene(root));
            detailsStage.getIcons().add(new Image("images/icon.png"));
            detailsStage.setTitle("Watch Collection - " + organizationName.getText() + " " + modelName.getText() + " " + reference.getText());
            detailsStage.setResizable(false);

            // Open the new screen without closing the previous one
            detailsStage.setOnHidden(event -> currentStage.show());
            detailsStage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}