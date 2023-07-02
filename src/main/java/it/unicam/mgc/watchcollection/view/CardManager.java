package it.unicam.mgc.watchcollection.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Provides methods for creating and configuring
 * cards that represent watches in an application.
 */
public class CardManager implements Manager<LinkedHashMap<String, String>, VBox> {

    /**
     * Create a card for each watch in the list, and
     * add the card to a TilePane.
     *
     * @param list              List containing the parsed of a SPARQL query.
     * @param tilePane          TilePane where to add cards.
     *
     * @throws IOException      File loader exception.
     */
    public void createCards(ArrayList<LinkedHashMap<String, String>> list, TilePane tilePane) throws IOException {

        for (LinkedHashMap<String, String> hashMap : list) {
            // Card creation
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/card.fxml"));
            VBox card = fxmlLoader.load();

            // Set the watch information in the card
            setData(hashMap, card);
            // Add card to TilePane
            tilePane.getChildren().add(card);
        }
    }

    /**
     * Set the basic information of a watch on a card.
     *
     * @param data      HashMap containing basic clock information.
     * @param card      Card (Vbox) where to add the information.
     */
    @Override
    public void setData(LinkedHashMap<String, String> data, VBox card) {

        ObservableList<Node> cardElements = card.getChildren();
        for (Node node : cardElements) {
            if (node instanceof ImageView) {
                setImage(node, data.get(node.getId()));
            } else if (node instanceof Text) {
                setText(node, data.get(node.getId()));
            }
        }
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
}
