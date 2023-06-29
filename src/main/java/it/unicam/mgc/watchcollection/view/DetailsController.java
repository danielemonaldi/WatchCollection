package it.unicam.mgc.watchcollection.view;

import it.unicam.mgc.watchcollection.control.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DetailsController {

    // FXML UI objects
    @FXML
    private ImageView watchImage;

    @FXML
    private ImageView movementImage;

    @FXML
    private Text caseMaterial;

    @FXML
    private Button returnButton;

    @FXML
    private Text glassMaterial;

    @FXML
    private Text introductionYear;

    @FXML
    private Text listPrice;

    @FXML
    private Text modelName;

    @FXML
    private Text organizationName;

    @FXML
    private Text referenceString;

    @FXML
    private Text strapMaterial;

    @FXML
    private Text watchType;

    @FXML
    private Text waterResistance;

    @FXML
    private Text diameter;

    @FXML
    private Text lugToLug;

    @FXML
    private Text thickness;

    @FXML
    private Text handle;

    @FXML
    private Text movementName;

    @FXML
    private Text movementType;

    @FXML
    private Text reserve;

    @FXML
    private Text jewels;

    @FXML
    private Text varText;

    @FXML
    private Text varValue;

    @FXML
    private Text coscCertification;

    @FXML
    private Text complication;

    @FXML
    private Text watchMaker;

    Database database = new Database();

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
     * @param reference     Watch reference string.
     */
    public void setData(String reference) {

        // Get watch detailed information
        ArrayList<LinkedHashMap<String, String>> detailData = database.getWatchDetails(reference);
        LinkedHashMap<String, String> details = detailData.get(0);

        // Set watch detailed information
        Image waImage = new Image(details.get("watchImage"));
        watchImage.setImage(waImage);
        organizationName.setText(details.get("organizationName"));
        modelName.setText(details.get("modelName"));
        referenceString.setText(details.get("referenceString"));
        introductionYear.setText(details.get("introductionYear"));
        listPrice.setText("$ " + details.get("listPrice"));
        watchType.setText(details.get("watchTypeName"));
        waterResistance.setText(details.get("waterResistance") + " m");
        caseMaterial.setText(details.get("caseMaterialName"));
        strapMaterial.setText(details.get("strapMaterialName"));
        glassMaterial.setText(details.get("glassMaterialName"));

        // Get watch dimensions
        ArrayList<LinkedHashMap<String, String>> dimensionData = database.getWatchDimensions(reference);
        LinkedHashMap<String, String> dimension = dimensionData.get(0);

        // Set watch dimensions
        diameter.setText(dimension.get("diameter") + " mm");
        lugToLug.setText(dimension.get("lugToLug") + " mm");
        thickness.setText(dimension.get("thickness") + " mm");
        handle.setText(dimension.get("handle") + " mm");

        // Get movement information
        ArrayList<LinkedHashMap<String, String>> movementData = database.getWatchMovement(reference);
        LinkedHashMap<String, String> movement = movementData.get(0);

        // Set movement information
        watchMaker.setText(movement.get("watchMaker"));
        movementName.setText(movement.get("movementName"));
        reserve.setText(movement.get("reserve") + " h");
        jewels.setText(movement.get("jewels"));
        complication.setText(movement.get("complicationName"));
        Image movImage = new Image(movement.get("movementImage"));
        movementImage.setImage(movImage);

        if (movement.containsKey("frequency")) {
            varText.setText("Frequency:");
            varValue.setText(movement.get("frequency") + " VHP");
            if (movement.get("rotor").equals("true")) {
                movementType.setText("Mechanical Movement - Automatic winding");
            } else {
                movementType.setText("Mechanical Movement - Manual winding");
            }
        } else {
            movementType.setText("Quartz Movement");
            varText.setText("Battery Type:");
            varValue.setText(movement.get("batteryType"));
        }

        if (movement.get("coscCertification").equals("true")) {
            coscCertification.setText("Yes");
        } else coscCertification.setText("No");
    }

    /**
     *  Close the current window.
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
}
