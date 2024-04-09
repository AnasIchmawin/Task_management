package presentation.listes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ListFormController {

    public static void handleAjouterButtonAction(GridPane gridPane) {

        Button newListButton = new Button("  Liste  ");
        newListButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 170px; " +
                "-fx-min-height: 60px;"+
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        int colIndex = gridPane.getChildren().size() % 6; // Calcul de la colonne
        int rowIndex = gridPane.getChildren().size() / 6; // Calcul de la ligne

        // Charger l'icône de la liste
        try {
            Image ListeIcon = new Image("file:./Pictures/to-do.png");
            ImageView ListIconView = new ImageView(ListeIcon);
            ListIconView.setFitWidth(15);
            ListIconView.setFitHeight(15);
            newListButton.setGraphic(ListIconView);
            gridPane.add(newListButton, colIndex, rowIndex);
            
        } catch (Exception e) {
            System.out.println("Erreur de charger l'icône de la liste");
        }
    }
}
