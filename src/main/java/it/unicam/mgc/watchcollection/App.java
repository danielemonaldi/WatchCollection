package it.unicam.mgc.watchcollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Main UI settings
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ui.fxml")));
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setTitle("Watch Collection");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}