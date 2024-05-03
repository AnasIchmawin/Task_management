package presentation.tache_detail;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class controleur {

    public void handleAjouterButtonAction(HBox gridPane) {
    }
    public void handleAjouterButtonAction(GridPane gridPane) {
        
        Button newListButton = new Button("Liste");
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 70px; " +
                "-fx-min-height: 40px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 10px;");
                int colIndex = gridPane.getChildren().size() % 3; // Calculating column index
                int rowIndex = gridPane.getChildren().size() / 3; // Calculating row index

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
    public void handleSaveButton(GridPane gridPane) {
        // Save button;
    }

}
