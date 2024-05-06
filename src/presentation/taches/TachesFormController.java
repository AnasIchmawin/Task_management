package presentation.taches;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TachesFormController {

    public TachesFormController() {
        super();
    }

    public static void handleAjouterButtonAction(GridPane gridPane,String taskName) {
        Button cloneButton = new Button("");
        Button deleteButton = new Button("");
        CheckBox taskCheckBox = new CheckBox(taskName);
        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 700px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 17px;"+
                "-fx-padding: 0px 0px 0px 5px;");
        taskCheckBox.setAlignment(Pos.CENTER_LEFT); // Align the checkbox to the left
        deleteButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px;" +
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

        cloneButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        //clonner la tache normale et tack checker
        cloneButton.setOnAction(e -> {
            handleAjouterButtonAction(gridPane,taskName);
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
                            "-fx-min-width: 700px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 17px;"+
                            "-fx-padding: 0px 0px 0px 5px;");
                    deleteButton.setStyle("-fx-background-color: #FF7E67; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-min-width: 30px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 13px;");
                    cloneButton.setStyle("-fx-background-color: #FF7E67; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-min-width: 30px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 13px;");
                } else {
                    taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-min-width: 700px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 17px;"+
                            "-fx-padding: 0px 0px 0px 5px;");
                    deleteButton.setStyle("-fx-background-color: #112D4E; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-min-width: 30px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 13px;");
                    cloneButton.setStyle("-fx-background-color: #112D4E; " +
                            "-fx-background-radius: 10px; " +
                            "-fx-min-width: 30px; " +
                            "-fx-min-height: 30px;" +
                            "-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 13px;");
                }
            }
        });
    }

    //handle the search button
    public static void handleSearchButtonAction(GridPane gridPane, String taskName) {
        
    }
}
