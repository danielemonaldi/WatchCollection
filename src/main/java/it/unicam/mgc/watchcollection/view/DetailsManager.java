package it.unicam.mgc.watchcollection.view;

import it.unicam.mgc.watchcollection.control.DetailsController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Provides methods for creating and configuring
 * of the window containing the detailed information of a watch.
 */
public class DetailsManager implements Manager<LinkedHashMap<String, String>, HBox> {

    /**
     * Create and open the screen to view the
     * detailed information of a specific watch.
     *
     * @param window                UI Window
     * @param referenceString       Reference string of the watch.
     * @param organizationName      Name of the organization that make the watch.
     * @param modelName             Name of the watch model.
     */
    public void openDetailScene(Window window, String referenceString, String organizationName, String modelName) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/details.fxml"));
            Parent root = fxmlLoader.load();
            DetailsController detailsController = fxmlLoader.getController();

            // Set the reference whose detailed information are to be displayed
            detailsController.setReference(referenceString);

            // Hide the current screen
            Stage currentStage = (Stage) window;
            currentStage.hide();

            // UI settings
            Stage detailsStage = new Stage();
            detailsStage.setScene(new Scene(root));
            detailsStage.getIcons().add(new Image("images/icon.png"));
            detailsStage.setTitle("Watch Collection - " + organizationName + " " + modelName + " " + referenceString);
            detailsStage.setResizable(false);

            // Open the new screen without closing the previous one
            detailsStage.setOnHidden(event -> currentStage.show());
            detailsStage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set the detailed information of the watch on the UI
     *
     * @param data      Watch detailed information.
     * @param hBox      FXML element that contains the elements to set.
     */
    @Override
    public void setData(LinkedHashMap<String, String> data, HBox hBox) {

        // Get all hBox children elements
        ArrayList<Node> element = retrieveChildNodes(hBox);

        for (Node node : element) {
            // Set UI images
            if (node instanceof ImageView) {
                if (data.containsKey(node.getId())) {
                    setImage(node, data.get(node.getId()));
                }
            // Set UI text
            } else if (node instanceof Text) {
                if (data.containsKey(node.getId())) {
                    // Set "coscCertification" value
                    if (node.getId().equals("coscCertification")) {
                        setBoolean(node, data.get(node.getId()));
                    } else {
                        setText(node, data.get(node.getId()));
                    }
                }
            }
        }
    }

    /**
     * Get all child elements of an FXML element.
     *
     * @param node      Element of which to obtain the children.
     *
     * @return          List of children elements.
     */
    public ArrayList<Node> retrieveChildNodes(Node node) {

        ArrayList<Node> nodes = new ArrayList<>();

        if (node instanceof Parent parent) {
            ObservableList<Node> children = parent.getChildrenUnmodifiable();

            for (Node child : children) {
                nodes.add(child);

                // If the child is itself a container, make a recursive call
                if (child instanceof Parent) {
                    nodes.addAll(retrieveChildNodes(child));
                }
            }
        }

        return nodes;
    }

    /**
     * Set the text of a FXML node of type Text.
     *
     * @param node      FXML Node to which to set the text.
     * @param text      Text to set.
     */
    private void setText(Node node, String text) {
        Text textView = (Text) node;
        textView.setText(text);
    }

    /**
     * Set the image of a FXML node of type ImageView.
     *
     * @param node          FXML node to which to set the image.
     * @param imageURL      Image URL.
     */
    private void setImage(Node node, String imageURL) {
        ImageView imageView = (ImageView) node;
        Image image = new Image(imageURL);
        imageView.setImage(image);
    }

    /**
     * converts a typical boolean "yes/no" value to:
     * "yes" when it's TRUE
     * "no" when it's FALSE
     *
     * @param node      FXML node to which to set the value.
     * @param value     String with typical boolean "yes/no" value.
     */
    private void setBoolean(Node node, String value) {
        if (value.equals("true")) {
            setText(node, "yes");
        } else setText(node, "no");
    }
}
