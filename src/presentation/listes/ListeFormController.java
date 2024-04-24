package presentation.listes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ListeFormController {

    public ListeFormController() {
        super();
    }

    public void handleAjouterButtonAction(GridPane gridPane) {
        Button newListButton = new Button("Liste");
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 170px; " +
                "-fx-min-height: 60px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        int colIndex = gridPane.getChildren().size() % 5; // Calculating column index
        int rowIndex = gridPane.getChildren().size() / 5; // Calculating row index

        // Load list icon
        try {
            Image listIcon = new Image("file:./Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newListButton.setGraphic(listIconView);
            gridPane.add(newListButton, colIndex, rowIndex);

        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }
    }

}