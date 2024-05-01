package presentation.projets;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ProjetsFormController {

    public ProjetsFormController() {
        super();
    }

    public static void handleAjouterButtonAction(GridPane gridPane) {

        Button newListButton = new Button("  Projet  ");
        
        newListButton.getStyleClass().add("newListButton-style");

        int colIndex = gridPane.getChildren().size() % 5; // Calcul de la colonne
        int rowIndex = gridPane.getChildren().size() / 5; // Calcul de la ligne

        // Charger l'icône de la liste
        try {
            Image ListeIcon = new Image("file:./Pictures/to-do.png");
            ImageView ListIconView = new ImageView(ListeIcon);
            ListIconView.setFitWidth(15);
            ListIconView.setFitHeight(15);
            newListButton.setGraphic(ListIconView);
            gridPane.add(newListButton, colIndex, rowIndex);
            
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }
    }
}
