package it.unicam.mgc.watchcollection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class CardController {

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

    public void setData(LinkedHashMap<String, String> data) {

        Image waImage = new Image(data.get("watchImage"));
        watchImage.setImage(waImage);
        organizationName.setText(data.get("organizationName"));
        modelName.setText(data.get("modelName"));
        reference.setText(data.get("referenceString"));

        details.setOnAction(event -> {
            openDetailScene();
        });
    }

    private void openDetailScene() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/details.fxml"));
            Parent root = fxmlLoader.load();

            DetailsController detailsController = fxmlLoader.getController();
            detailsController.setReference(reference.getText());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.getIcons().add(new Image("images/icon.png"));
            stage.setTitle("Watch Collection - " + organizationName.getText() + " " + modelName.getText() + " " + reference.getText());
            stage.setResizable(false);

            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}