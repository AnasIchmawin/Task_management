package presentation.taches;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.Gestionnaire.GestionnaireListe;
import metier.Gestionnaire.GestionnaireTache;
import presentation.GetTaskFromCalendar.GetTaskCalendar;
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;
import presentation.tache_ajoute.addTacheview;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class TachesFormController {
    private TachesFormView tacheView;
    private static TachesFormModel tacheModel;
    private static GestionnaireTache gestionnaireTache;
    private static GestionnaireListe gestionnaireListe;
    private String dateTaskFormated;
    private static ListeFormController listeFormController;

    @SuppressWarnings("static-access")
    public TachesFormController(TachesFormView tacheView, ListeFormController listeFormController) {
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireListe = new GestionnaireListe();
        this.listeFormController = listeFormController;
        this.tacheView = tacheView;
        this.tacheModel = new TachesFormModel(getTacheMap());
        this.FillChamps();
    }

    public void handleAjouterButtonAction() {
        addTacheview view = new addTacheview(this);
        view.start(new Stage());   
    }

    public void handleSaveButtonAction() {
        // gestionnaireTache.updateTask(getListId(), );//id,title,description
    }

    // handleOrdonnerButtonAction
    public void handleOrdonnerButtonAction() {
        displayedTasks(true);
    }

    // displayTaches
    public void displayedTasks(boolean isSorted) {
        tacheModel.setdisplayedTasks(getTacheMap());
        if (isSorted) {
            tacheModel.sortTasksByTitle();
        }
        tacheView.getZoneTaches().getChildren().clear();
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : tacheModel.getdisplayedTasks().entrySet()) {
            createTask(tacheView.getZoneTaches(), entry.getValue(), getTaskEtat(entry.getKey()), entry.getKey());
            tacheModel.addGridInfosCase(List.of(rowCount, colCount), entry.getKey());

            if (++colCount == 3) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    // create task
    public static void createTask(GridPane gridPane, String taskName, Boolean isChecked, String tacheId) {
        Button cloneButton = createButtonWithIcon("file:Pictures/clone.png");
        Button deleteButton = createButtonWithIcon("file:Pictures/delete.png");
        Button taskButton = new Button("");
        CheckBox taskCheckBox = createTaskCheckBox(taskName, isChecked);

        configureButtons(gridPane, cloneButton, deleteButton, taskButton, taskCheckBox, isChecked, tacheId);
        setTaskRow(gridPane, deleteButton, cloneButton, taskCheckBox, taskButton);
        updateTaskState(taskCheckBox, deleteButton, cloneButton, tacheId, isChecked);
        configureTaskCheckBoxListener(taskCheckBox, deleteButton, cloneButton, tacheId);
    }

    private void FillChamps() {
        this.tacheView.setTitle(getListTitle());
        this.tacheView.setDescription(getListDescription());
        this.displayedTasks(true);
        this.ServeillerButtons();
    }

    private void ServeillerButtons() {
        SurveillerButton(tacheView.getListesButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getProjectsButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getArchiveButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getAjouterButton(), "150", "40", "#3F72AF");
    }

    // Méthodes auxiliaires de création d'éléments
    private static Button createButtonWithIcon(String imagePath) {
        Button button = new Button("");
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        return button;
    }

    // Configuration des éléments
    private static CheckBox createTaskCheckBox(String taskName, Boolean isChecked) {
        CheckBox taskCheckBox = new CheckBox(taskName);
        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 700px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 17px;" +
                "-fx-padding: 0px 0px 0px 5px;");
        taskCheckBox.setAlignment(Pos.CENTER_LEFT);
        taskCheckBox.setSelected(isChecked);
        return taskCheckBox;
    }

    // Configuration des éléments
    private static void configureButtons(GridPane gridPane, Button cloneButton, Button deleteButton, Button taskButton,
            CheckBox taskCheckBox, Boolean isChecked, String tacheId) {
        configureDeleteButton(gridPane, deleteButton, cloneButton, taskCheckBox, tacheId);
        configureCloneButton(gridPane, cloneButton, tacheId);
        configureTaskButton(gridPane, taskButton);
    }

    private static void configureDeleteButton(GridPane gridPane, Button deleteButton, Button cloneButton,
            CheckBox taskCheckBox, String tacheId) {
        deleteButton.setOnAction(e -> {
            removeTask(gridPane, taskCheckBox, deleteButton, cloneButton);
            gestionnaireTache.deleteTask(tacheId);
            gestionnaireListe.deleteTacheFromListe(listeFormController.getListId(), tacheId);
        });
    }

    private static void configureCloneButton(GridPane gridPane, Button cloneButton, String tacheId) {
        cloneButton.setOnAction(e -> {
            createTask(gridPane, getnameTask(tacheId), tacheModel.getTaskEtat(tacheId), tacheId);
            gestionnaireTache.cloneTask(tacheId);
            gestionnaireListe.setTacheToListe(listeFormController.getListId(), gestionnaireTache.getLastTacheId());
        });
    }

    private static void configureTaskButton(GridPane gridPane, Button taskButton) {
        taskButton.setStyle("-fx-background-color: transparent; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 665px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        taskButton.setOnAction(e -> displayMessageDialog());
    }

    private static void configureTaskCheckBoxListener(CheckBox taskCheckBox, Button deleteButton, Button cloneButton,
            String tacheId) {
        taskCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateTaskState(taskCheckBox,
                deleteButton, cloneButton, tacheId, newValue));
    }

    // retourne le nom de la tache
    private static String getnameTask(String tacheId) {
        for (Map.Entry<String, String> entry : tacheModel.getdisplayedTasks().entrySet()) {
            if (entry.getKey().equals(tacheId)) {
                return entry.getValue();
            }
        }
        return "";
    }

    // Configuration de la ligne de la tâche
    private static void setTaskRow(GridPane gridPane, Button deleteButton, Button cloneButton, CheckBox taskCheckBox,
            Button taskButton) {
        int row = gridPane.getRowCount();
        gridPane.add(deleteButton, 3, row);
        gridPane.add(cloneButton, 4, row);
        gridPane.add(taskCheckBox, 5, row);
        gridPane.add(taskButton, 5, row);
        GridPane.setHalignment(taskButton, HPos.RIGHT);
    }

    // Autres méthodes auxiliaires
    @SuppressWarnings("static-access")
    private static void removeTask(GridPane gridPane, CheckBox taskCheckBox, Button deleteButton, Button cloneButton) {
        gridPane.getChildren().removeAll(taskCheckBox, deleteButton, cloneButton);
        // Suppression de l'espace de la tâche supprimée
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            if (rowIndex != null && rowIndex > gridPane.getRowIndex(taskCheckBox)) {
                GridPane.setRowIndex(node, rowIndex - 1);
            }
        }
    }

    private static void displayMessageDialog() {
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
    }

    private static void updateTaskState(CheckBox taskCheckBox, Button deleteButton, Button cloneButton, String tacheId,
            Boolean newValue) {
        if (newValue) {
            taskCheckBox.setStyle("-fx-background-color: #FF7E67; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 700px; " +
                    "-fx-min-height: 30px;" +
                    "-fx-text-fill: #ffffff;" +
                    "-fx-font-size: 17px;" +
                    "-fx-padding: 0px 0px 0px 5px;");
            if (deleteButton != null)
                deleteButton.setStyle("-fx-background-color: #FF7E67; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-min-width: 30px; " +
                        "-fx-min-height: 30px;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 13px;");
            if (cloneButton != null)
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
                    "-fx-font-size: 17px;" +
                    "-fx-padding: 0px 0px 0px 5px;");
            if (deleteButton != null)
                deleteButton.setStyle("-fx-background-color: #112D4E; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-min-width: 30px; " +
                        "-fx-min-height: 30px;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 13px;");
            if (cloneButton != null)
                cloneButton.setStyle("-fx-background-color: #112D4E; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-min-width: 30px; " +
                        "-fx-min-height: 30px;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 13px;");
            setTaskEtat(tacheId, false);
        }
    }

    public void searchTache(String searchText) {
        int colCount = 5;
        int rowCount = 0;

        tacheView.getZoneTaches().getChildren().clear();

        for (Map.Entry<String, String> entry : tacheModel.getdisplayedTasks().entrySet()) {
            String taskName = entry.getValue().toLowerCase();
            if (taskName.contains(searchText.toLowerCase())) {
                createTask(tacheView.getZoneTaches(), taskName, getTaskEtat(entry.getKey()), entry.getKey());
                tacheModel.addGridInfosCase(List.of(rowCount, colCount), entry.getKey());
                rowCount++;
            }
        }

    }

    private LinkedHashMap<String, String> getTacheMap() {
        LinkedHashMap<String, Boolean> taches = gestionnaireListe.getTaches(TachesFormController.listeFormController.getListId());

        LinkedHashMap<String, String> tacheMap = new LinkedHashMap<>();
        for (String tacheId : taches.keySet()) {
            String tacheTitle = gestionnaireTache.getTitle(tacheId);
            tacheMap.put(tacheId, tacheTitle);
        }
        return tacheMap;
    }

    // handleListesButtonAction
    public void handleListesButtonAction() {
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        ListeFormView liste = new ListeFormView();
        liste.start(stage);
    }

    // handleArchivesButtonAction
    public void handleArchiveButtonAction() {
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        ArchiveFormView archive = new ArchiveFormView();
        archive.start(stage);
    }

    // handleProjectsButtonAction
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
        tacheModel.addTaskEtat(tacheId, etat);
    }

    public String getListTitle() {
        return gestionnaireListe.getListTitle(TachesFormController.listeFormController.getListId());
    }

    public String getListDescription() {
        return gestionnaireListe.getListDescription(TachesFormController.listeFormController.getListId());
    }

    public String getListId() {
        return TachesFormController.listeFormController.getListId();
    }

    public void handleConfirmerButtonAction() {
        try {
            String dateTask = tacheView.getDateTask();
            // convert format to DD/MM/YYYY
            String[] date = dateTask.split("-");
            this.setDateTaskFormated(date[2] + "/" + date[1] + "/" + date[0]);
            GetTaskCalendar getTaskCalendar = new GetTaskCalendar(this);
            getTaskCalendar.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Veuillez saisir une date valide");
            alert.showAndWait();
        }

    }

    public String getDateTaskFormated() {
        return dateTaskFormated;
    }

    public void setDateTaskFormated(String dateTaskFormated) {
        this.dateTaskFormated = dateTaskFormated;
    }

    public void SurveillerButton(Button button, String width, String height, String color) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: #8E9EB2; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-radius: 10px; " +
                    "-fx-pref-width:" + width + "; " +
                    "-fx-background-color: " + color + ";" +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 13px;");
            button.setCursor(javafx.scene.Cursor.DEFAULT);
        });
    }
}
