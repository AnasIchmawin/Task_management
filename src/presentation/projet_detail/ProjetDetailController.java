package presentation.projet_detail;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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









    //handleajouterTacheButtonAction
    public void handleAjouterTacheButtonAction(GridPane gridPane) {
        Button deleteButton = new Button("");
        Button cloneButton = new Button("");
            CheckBox taskCheckBox = new CheckBox("taskName");
            taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 430px; " +
                    "-fx-min-height: 20px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 17px;"+
                    "-fx-padding: 0px 0px 0px 5px;");
            taskCheckBox.setAlignment(Pos.CENTER_LEFT); // Align the checkbox to the left
            deleteButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 25px; " +
                    "-fx-min-height: 25px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 13px;");
            
            cloneButton.setStyle("-fx-background-color: #112D4E; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 25px; " +
                    "-fx-min-height: 25px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 13px;");

            deleteButton.setOnAction(e -> {
                gridPane.getChildren().removeAll(taskCheckBox, deleteButton, cloneButton);
                //delete the space of the deleted task
                for (Node node : gridPane.getChildren()) {
                    Integer rowIndex = GridPane.getRowIndex(node);
                    if (rowIndex != null && rowIndex > gridPane.getRowIndex(taskCheckBox)) {
                        GridPane.setRowIndex(node, rowIndex - 1);
                    }
                }
            });

            cloneButton.setOnAction(e -> {
                handleAjouterTacheButtonAction(gridPane);
            });

            int row = gridPane.getRowCount();
            gridPane.add(deleteButton, 3, row);
            gridPane.add(cloneButton, 4, row);
            gridPane.add(taskCheckBox, 5, row);

            taskCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                //change color checkbox and thier buttons when checked
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        taskCheckBox.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 450px; " +
                                "-fx-min-height: 20px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 17px;"+
                                "-fx-padding: 0px 0px 0px 5px;");
                        deleteButton.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                        cloneButton.setStyle("-fx-background-color: #FF7E67; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                    } else {
                        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 450px; " +
                                "-fx-min-height: 20px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 17px;"+
                                "-fx-padding: 0px 0px 0px 5px;");
                        deleteButton.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                        cloneButton.setStyle("-fx-background-color: #112D4E; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-min-width: 25px; " +
                                "-fx-min-height: 25px;" +
                                "-fx-text-fill: #ffffff;" +
                                "-fx-font-size: 13px;");
                    }
                }
            });
    }

}
