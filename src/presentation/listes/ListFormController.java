package presentation.listes;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    public static void handleEditerButton(Button saveButton,TextArea ZoneDescription){
        ZoneDescription.setEditable(true);
        saveButton.setVisible(true);

    };
    
    public static void handleSaveButton(Button button1,Button button2,Button button3,Button button4 ,
     TextArea ZoneDescription){
        ZoneDescription.setEditable(false);
            button1.setVisible(true);
            button2.setVisible(false);
            button3.setVisible(false);
            button4.setVisible(false);

    };

        //handleModifierButton
        public static void handleModifierButton(Button button1, Button button2, Button button3,Button button4){
            button1.setVisible(false);
            button2.setVisible(true);
            button3.setVisible(true);
            button4.setVisible(true);
    };
}
