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

    public static void handleAjouterButtonAction(GridPane gridPane) {
        CheckBox taskCheckBox = new CheckBox("New Task "+gridPane.getRowCount());
        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 700px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 17px;");
        taskCheckBox.setAlignment(Pos.CENTER_LEFT); // Align the checkbox to the left
        Button deleteButton = new Button("");
        deleteButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");

        Button cloneButton = new Button("");
        cloneButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        int row = gridPane.getRowCount();
        gridPane.add(deleteButton, 3, row);
        gridPane.add(cloneButton, 4, row);
        gridPane.add(taskCheckBox, 5, row);
        
    }
}
