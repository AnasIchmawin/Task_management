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
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.GestionnaireListe;
import metier.GestionnaireTache;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.archive.modele;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TachesFormController {
    private TachesFormView tacheView;
    private static TachesFormModel tacheModel;
    private static GestionnaireTache gestionnaireTache;
    private GestionnaireListe gestionnaireListe;
    private Map<List<Integer>, List<String>> gridCaseInfos;
    private String listId;

    public TachesFormController(TachesFormView tacheView) {
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireListe = new GestionnaireListe();
        this.tacheView = tacheView;
        this.listId = tacheView.getIdList();
        this.tacheModel = new TachesFormModel(getTacheMap());
    }

    // public TachesFormController(String listId) {
    //     this.listId = listId;
    //     this.gestionnaireTache = new GestionnaireTache();
    //     this.gestionnaireListe = new GestionnaireListe();
    //     this.tacheModel = new TachesFormModel(getTacheMap());
    // }

    public static void handleAjouterButtonAction(GridPane gridPane,String taskName) {
        // createTask(gridPane, taskName, true,);
        //save in database
    }

    //handleOrdonnerButtonAction
    public void handleOrdonnerButtonAction() {
        displayTaches(true);
    }

    //displayTaches
    public void displayTaches(boolean isSorted) {
        tacheModel.setTaches(getTacheMap());
        if (isSorted) {
            tacheModel.sortTaches();
        }
        tacheView.getZoneTaches().getChildren().clear();
        gridCaseInfos = new LinkedHashMap<>();
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String>  entry : tacheModel.getTaches().entrySet()) {
            createTask(tacheView.getZoneTaches(), entry.getValue(), getTaskEtat(entry.getKey()), entry.getKey());
            gridCaseInfos.put(List.of(rowCount, colCount), List.of(entry.getKey(), entry.getValue()));

            if (++colCount == 3) {
                colCount = 0;
                rowCount++;
            }
        }
    } 

    //create task
    public static void createTask(GridPane gridPane, String taskName, Boolean isChecked, String tacheId) {
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
            createTask(gridPane, taskCheckBox.getText(), isChecked, tacheId);
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

        if (isChecked) {
            taskCheckBox.setSelected(true);
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
        }

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
                    setTaskEtat(tacheId, true);
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
                    setTaskEtat(tacheId, false);
                }
            }
        });
    }

    public void searchTache(String searchText){
        int colCount = 5;
        int rowCount = 0;

        tacheView.getZoneTaches().getChildren().clear();

        for (Map.Entry<String, String> entry : tacheModel.getTaches().entrySet()) {
            String taskName = entry.getValue().toLowerCase();
            if (taskName.contains(searchText.toLowerCase())) {
                createTask(tacheView.getZoneTaches(), taskName, getTaskEtat(entry.getKey()), entry.getKey());
                gridCaseInfos.put(List.of(rowCount, colCount), List.of(taskName, entry.getKey()));
                rowCount++;
            }
        }
        
    }

    private LinkedHashMap<String, String> getTacheMap() {
        LinkedHashMap<String,Boolean> taches = gestionnaireListe.getTaches(listId);

        LinkedHashMap<String, String> tacheMap = new LinkedHashMap<>();
        for (String tacheId : taches.keySet()) {
            String tacheTitle = gestionnaireTache.getTitle(tacheId);
            tacheMap.put(tacheId, tacheTitle);
        }
        return tacheMap;
    }

    //handleListesButtonAction
    public void handleListesButtonAction() {
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        ListeFormView liste = new ListeFormView();
        liste.start(stage);
    }

    //handleArchivesButtonAction
    public void handleArchiveButtonAction() {
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        ArchiveFormView archive = new ArchiveFormView();
        archive.start(stage);
    }

    //handleProjectsButtonAction
    public void handleProjectsButtonAction() {
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

    public Boolean getTaskEtat(String tacheId) {
        return gestionnaireTache.getTaskEtat(tacheId);
    }

    public static void setTaskEtat(String tacheId, Boolean etat) {
        gestionnaireTache.setTaskEtat(tacheId, etat);
    }

    public String getListTitle() {
        return gestionnaireListe.getListTitle(listId);
    }

    public String getListDescription() {
        return gestionnaireListe.getListDescription(listId);
    }
}
