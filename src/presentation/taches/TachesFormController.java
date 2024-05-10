package presentation.taches;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TachesFormController {

    public TachesFormController() {
        super();
    }

    public static void handleAjouterButtonAction(GridPane gridPane,String taskName) {
        Button cloneButton = new Button("");
        Button deleteButton = new Button("");
        Button taskButton = new Button("");
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

        taskButton.setStyle("-fx-background-color: transparent; " +
        "-fx-background-radius: 10px; " +
        "-fx-min-width: 665px; " +
        "-fx-min-height: 30px;" +
        "-fx-text-fill: #ffffff;" +
        "-fx-font-size: 13px;");

        int row = gridPane.getRowCount();
        gridPane.add(deleteButton, 3, row);
        gridPane.add(cloneButton, 4, row);
        gridPane.add(taskCheckBox, 5, row);
        gridPane.add(taskButton, 5, row);
        GridPane.setHalignment(taskButton, HPos.RIGHT);


        taskButton.setOnAction(e -> {
    // Création d'une nouvelle fenêtre de dialogue
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setTitle("Message");
    VBox dialogVbox = new VBox(20);
    dialogVbox.setAlignment(Pos.CENTER);
    dialogVbox.getChildren().add(new Text("Ach ban lik a mourad 😎😛"));
    dialogVbox.setStyle("-fx-font-size: 20px;");
    Scene dialogScene = new Scene(dialogVbox, 400, 200);
    dialogStage.setScene(dialogScene);
    dialogStage.show();
});

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

    //handleAjouterSeanceButtonAction
    public static void handleAjouterSeanceButtonAction(GridPane gridPane) {

    }

    //handle the search button
    public static void handleSearchButtonAction(GridPane gridPane, String taskName) {
        
    }
}
