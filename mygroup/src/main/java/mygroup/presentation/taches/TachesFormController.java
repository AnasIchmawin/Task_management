package mygroup.presentation.taches;

import java.util.LinkedHashMap;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireListe;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.GetTaskFromCalendar.GetTaskFromCalendar;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormController;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormView;
import mygroup.presentation.tache_ajoute.addTacheview;
import mygroup.presentation.tache_detail.tacheDetailView;

public class TachesFormController {
    private TachesFormView tacheView;
    private TachesFormModel tacheModel;
    private GestionnaireTache gestionnaireTache;
    private GestionnaireListe gestionnaireListe;
    private String dateTaskFormated;
    private ListeFormController listeFormController;

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

    // handleOrdonnerButtonAction
    public void handleOrdonnerButtonAction() {
        displayedTasks(true);
    }

    // displayTaches
    public void displayedTasks(boolean isSorted) {
        tacheModel.setDisplayedTasks(getTacheMap());
        if (isSorted) {
            tacheModel.sortTasksByTitle();
        }
        tacheView.getZoneTaches().getChildren().clear();
        int colCount = 0;
        @SuppressWarnings("unused")
        int rowCount = 0;
        int rowTask = 0;

        for (Map.Entry<String, String> entry : tacheModel.getDisplayedTasks().entrySet()) {
            Button taskButton = createTask(entry.getValue(), getTaskEtat(entry.getKey()), entry.getKey());
            taskButton.setOnAction(event -> handleButtonTaskAction(taskButton));
            tacheModel.putInGridInfoCase(rowTask, entry.getKey());
            rowTask++;

            if (++colCount == 3) {
                colCount = 0;
                rowCount++;
            }

        }
    }

    // ----------------

    // create task
    public Button createTask(String taskName, Boolean isChecked, String tacheId) {
        Button cloneButton = createButtonWithIcon("file:./mygroup/src/main/java/Pictures/clone.png");
        Button deleteButton = createButtonWithIcon("file:./mygroup/src/main/java/Pictures/delete.png");
        Button taskButton = new Button("");
        CheckBox taskCheckBox = createTaskCheckBox(taskName, isChecked);

        configureButtons(this.tacheView.getZoneTaches(), cloneButton, deleteButton, taskButton, taskCheckBox, isChecked,
                tacheId);
        setTaskRow(this.tacheView.getZoneTaches(), deleteButton, cloneButton, taskCheckBox, taskButton);
        updateTaskState(taskCheckBox, deleteButton, cloneButton, tacheId, isChecked);
        configureTaskCheckBoxListener(taskCheckBox, deleteButton, cloneButton, tacheId);

        return taskButton;
    }

    



    private void FillChamps() {
        this.tacheView.setTitle(getListTitle());
        this.tacheView.setDescription(getListDescription());
        this.displayedTasks(false);
        this.ServeillerButtons();
    }


    private void ServeillerButtons() {
        SurveillerButton(tacheView.getListesButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getProjectsButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getArchiveButton(), "100", "40", "#3F72AF");
        SurveillerButton(tacheView.getAjouterButton(), "150", "40", "#3F72AF");
    }

    // Méthodes auxiliaires de création d'éléments
    private Button createButtonWithIcon(String imagePath) {
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
    private CheckBox createTaskCheckBox(String taskName, Boolean isChecked) {
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
    private void configureButtons(GridPane gridPane, Button cloneButton, Button deleteButton, Button taskButton,
            CheckBox taskCheckBox, Boolean isChecked, String tacheId) {
        configureDeleteButton(gridPane, deleteButton, cloneButton, taskCheckBox, tacheId);
        configureCloneButton(gridPane, cloneButton, tacheId);
        configureTaskButton(gridPane, taskButton);
    }

    private void configureDeleteButton(GridPane gridPane, Button deleteButton, Button cloneButton,
            CheckBox taskCheckBox, String tacheId) {
        deleteButton.setOnAction(e -> {
            removeTask(gridPane, taskCheckBox, deleteButton, cloneButton);
            gestionnaireTache.deleteTask(tacheId);
            gestionnaireListe.deleteTacheFromListe(listeFormController.getListId(), tacheId);
        });
    }

    private void configureCloneButton(GridPane gridPane, Button cloneButton, String tacheId) {
        cloneButton.setOnAction(e -> {
            createTask(getnameTask(tacheId), tacheModel.getTaskEtat(tacheId), tacheId);
            gestionnaireTache.cloneTask(tacheId);
            gestionnaireListe.setTacheToListe(listeFormController.getListId(), gestionnaireTache.getLastTacheId());
        });
    }

    private void configureTaskButton(GridPane gridPane, Button taskButton) {
        taskButton.setStyle("-fx-background-color: transparent; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 665px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        taskButton.setOnAction(e -> displayMessageDialog());
    }

    private void configureTaskCheckBoxListener(CheckBox taskCheckBox, Button deleteButton, Button cloneButton,
            String tacheId) {
        taskCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateTaskState(taskCheckBox,
                deleteButton, cloneButton, tacheId, newValue));
    }

    // retourne le nom de la tache
    private String getnameTask(String tacheId) {
        for (Map.Entry<String, String> entry : tacheModel.getDisplayedTasks().entrySet()) {
            if (entry.getKey().equals(tacheId)) {
                return entry.getValue();
            }
        }
        return "";
    }

    // Configuration de la ligne de la tâche
    private void setTaskRow(GridPane gridPane, Button deleteButton, Button cloneButton, CheckBox taskCheckBox,
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
    private void removeTask(GridPane gridPane, CheckBox taskCheckBox, Button deleteButton, Button cloneButton) {
        gridPane.getChildren().removeAll(taskCheckBox, deleteButton, cloneButton);
        // Suppression de l'espace de la tâche supprimée
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            if (rowIndex != null && rowIndex > gridPane.getRowIndex(taskCheckBox)) {
                GridPane.setRowIndex(node, rowIndex - 1);
            }
        }
    }

    private void displayMessageDialog() {

        // tacheDetailController tacheDetailController = new
        // tacheDetailController(this);
        System.out.println("nous sommes dans deplaymessage");

    }

    private void updateTaskState(CheckBox taskCheckBox, Button deleteButton, Button cloneButton, String tacheId,
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
        @SuppressWarnings("unused")
        int colCount = 5;
        int rowCount = 0;

        tacheView.getZoneTaches().getChildren().clear();

        for (Map.Entry<String, String> entry : tacheModel.getDisplayedTasks().entrySet()) {
            String taskName = entry.getValue().toLowerCase();
            if (taskName.contains(searchText.toLowerCase())) {
                createTask(taskName, getTaskEtat(entry.getKey()), entry.getKey());
                tacheModel.putInGridInfoCase(rowCount, entry.getKey());
                rowCount++;
            }
        }

    }

    private LinkedHashMap<String, String> getTacheMap() {
        LinkedHashMap<String, Boolean> taches = gestionnaireListe.getTaches(listeFormController.getListId());

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

    public void setTaskEtat(String tacheId, Boolean etat) {
        gestionnaireTache.setTaskEtat(tacheId, etat);
        tacheModel.addTaskEtat(tacheId, etat);
    }

    public String getListTitle() {
        return gestionnaireListe.getListTitle(listeFormController.getListId());
    }

    public String getListDescription() {
        return gestionnaireListe.getListDescription(listeFormController.getListId());
    }

    public String getListId() {
        return listeFormController.getListId();
    }


    public void handleConfirmerButtonAction() {
        try {
            String dateTask = tacheView.getDateTask();
            // convert format to DD/MM/YYYY
            String[] date = dateTask.split("-");
            this.setDateTaskFormated(date[2] + "/" + date[1] + "/" + date[0]);
            GetTaskFromCalendar getTaskCalendar = new GetTaskFromCalendar(this);
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

    public void handleTitleConfButtonAction() {
        gestionnaireListe.updateListe(getListId(), tacheView.getTitle(), tacheView.getDescription());
    }

    private void handleButtonTaskAction(Button tasButton) {
        String listId = getTaskIdFromButton(tasButton);
        tacheModel.setTaskSelectedId(listId);
        tacheDetailView tacheview = new tacheDetailView(this);
        Stage stage = (Stage) tacheView.getZoneTaches().getScene().getWindow();
        tacheview.start(stage);
    }

    private String getTaskIdFromButton(Button taskbutton) {
        int rowButton = GridPane.getRowIndex(taskbutton);
        return this.tacheModel.getValueGrid(rowButton);

    }

    public String getTaskSelectedId() {
        return tacheModel.getTaskSelectedId();
    }


    public void addTask(String tacheId, String titre) {
        tacheModel.addTask(tacheId, titre);
        createTask(titre, false, tacheId );
        gestionnaireListe.setTacheToListe(listeFormController.getListId(), tacheId);
    }

    public void addTaskforList(String tacheId , String titre){
        tacheModel.addTask(tacheId, titre);
    }

    
}
