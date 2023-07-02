package it.unicam.mgc.watchcollection.control;

import it.unicam.mgc.watchcollection.view.DetailsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

public class CardController {

    // FXML UI objects
    @FXML
    private Text organizationName;

    @FXML
    private Text modelName;

    @FXML
    private Text referenceString;

    @FXML
    private Hyperlink details;

    private final DetailsManager detailsManager = new DetailsManager();

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
        detailsManager.openDetailScene(referenceString.getScene().getWindow(), referenceString.getText(), organizationName.getText(), modelName.getText());
    }
}