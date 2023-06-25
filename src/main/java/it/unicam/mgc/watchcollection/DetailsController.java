package it.unicam.mgc.watchcollection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DetailsController {

    @FXML
    private ImageView watchImage;

    @FXML
    private ImageView movementImage;

    @FXML
    private Text caseMaterial;

    @FXML
    private Button close;

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

    @FXML
    private Button addCollection;

    @FXML
    private Button addWishlist;

    private final String userEmail = "user@gmail.com";
    DatabaseUtility database = new DatabaseUtility();
    WishlistUtility wishlist = new WishlistUtility();

    public void setReference(String reference) {
        setData(reference);
    }

    public void initialize() throws IOException {
        close.setOnAction(event -> closeCurrentWindow());
        addWishlist.setOnAction(event -> addWatchWishlist());
    }

    private void setData(String reference) {

        ArrayList<LinkedHashMap<String, String>> detailData = database.getWatchDetails(reference);
        LinkedHashMap<String, String> details = detailData.get(0);

        Image returnImage = new Image("images/return.png");
        close.setGraphic(new ImageView(returnImage));

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

        ArrayList<LinkedHashMap<String, String>> dimensionData = database.getWatchDimension(reference);
        LinkedHashMap<String, String> dimension = dimensionData.get(0);

        diameter.setText(dimension.get("diameter") + " mm");
        lugToLug.setText(dimension.get("lugToLug") + " mm");
        thickness.setText(dimension.get("thickness") + " mm");
        handle.setText(dimension.get("handle") + " mm");

        ArrayList<LinkedHashMap<String, String>> movementData = database.getWatchMovement(reference);
        LinkedHashMap<String, String> movement = movementData.get(0);

        watchMaker.setText(movement.get("watchMaker"));
        movementName.setText(movement.get("movementName"));

        if (movement.get("rotor").equals("true")) {
            movementType.setText("Mechanical Movement- Automatic winding");
        } else if ((movement.get("rotor").equals("false"))) {
            movementType.setText("Mechanical Movement - Manual winding");
        } else movementType.setText("Quartz Movement");

        if (movement.containsKey("frequency")) {
            varText.setText("Frequency:");
            varValue.setText(movement.get("frequency") + " VHP");
        } else if (movement.containsKey("batteryType")){
            varText.setText("Battery Type:");
            varValue.setText(movement.get("batteryType"));
        }

        reserve.setText(movement.get("reserve") + " h");
        jewels.setText(movement.get("jewels"));

        if (movement.get("coscCertification").equals("true")) {
            coscCertification.setText("Yes");
        } else coscCertification.setText("No");

        complication.setText(movement.get("complicationName"));

        Image movImage = new Image(movement.get("movementImage"));
        movementImage.setImage(movImage);
    }

    private void addWatchWishlist() {
        wishlist.addWatchWishlist(this.userEmail, referenceString.getText());
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
