package presentation.projet_detail;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ProjetDetailController {

    
    public void handleAjouterButtonAction(GridPane gridPane) {
        Button newListButton = new Button("Seance  1");
        newListButton.getStyleClass().add("AjouterSeance-Style");
        GridPane.setHgrow(newListButton, Priority.ALWAYS); // Définir la croissance horizontale pour occuper toute la
                                                           // largeur disponible

        int colIndex = gridPane.getChildren().size() % 1; // Calcul de l'indice de colonne
        int rowIndex = gridPane.getChildren().size() / 1; // Calcul de l'indice de ligne

        gridPane.add(newListButton, colIndex, rowIndex);
    }

    public void handleSaveButton(GridPane gridPane) {
        // Save button;
    }

}
