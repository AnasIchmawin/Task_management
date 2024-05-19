package mygroup.presentation.projet_detail;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireDocument;
import mygroup.metier.Gestionnaire.GestionnaireProjet;
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.presentation.tache_ajoute.addTacheview;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormController;
import mygroup.presentation.projets.ProjetsFormView;

public class ProjetDetailController {
    @SuppressWarnings("unused")
    private GestionnaireDocument gestionnaireDocument;
    private GestionnaireSeance gestionnaireSeance;
    private GestionnaireProjet gestionnaireProjet;
    private GestionnaireTache gestionnaireTache;
    private ProjectDetailModel projectDetailModel;
    private ProjetDetailView projetDetailView;
    private ProjetsFormController projetsFormController;

    public ProjetDetailController(ProjetDetailView view, ProjetsFormController projetsFormController) {
        this.projetsFormController = projetsFormController;
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireProjet = new GestionnaireProjet();
        this.gestionnaireSeance = new GestionnaireSeance();
        this.gestionnaireDocument = new GestionnaireDocument();
        this.projetDetailView = view;
        this.projectDetailModel = new ProjectDetailModel();
        this.projectDetailModel.setProjetID(this.projetsFormController.getSelectedProjetId());
        this.FillChamps();

    }

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

    private void FillChamps() {
        this.projetDetailView.settitle(getProjetTitle());
        System.out.println("Title : " + getProjetTitle());
        this.projetDetailView.setDescription(getProjetDescription());
        System.out.println("Description : " + getProjetDescription());
        this.projetDetailView.setDateDebut(getStartDate());
        System.out.println("Date de début : " + getStartDate());
        this.projetDetailView.setDateFin(getEndDate());
        System.out.println("Date de fin : " + getEndDate());
        this.projetDetailView.setCategorie(getCategory());
        System.out.println("Categorie : " + getCategory());
        this.projetDetailView.setType(getType());
        System.out.println("Type : " + getType());
        this.displayedTasks();
        this.displayedSeances();
        this.ServeillerButtons();
    }

    public void displayedTasks() {

        projectDetailModel.setDisplayedTasks(getTacheMap());

        if (projectDetailModel.getDisplayedTasks().isEmpty())
            return;
        else {
           System.out.println("-------on a des taches-------");
            
        }
        projetDetailView.getZoneTaches().getChildren().clear();
        int colCount = 0;
        int rowCount = 0;
        // int rowTask = 0;

        for (Map.Entry<String, String> entry : projectDetailModel.getDisplayedTasks().entrySet()) {
            @SuppressWarnings("unused")
            Button taskButton = createTask(projetDetailView.getZoneTaches(), entry.getValue(),
                    getTaskEtat(entry.getKey()), entry.getKey());
            projectDetailModel.putInGridInfoCase(rowCount, entry.getKey());

            // rowTask++;
            if (++colCount == 3) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    private void displayedSeances() {
        projectDetailModel.setDisplayedSeances(getSeanceMap());
        GridPane gridPane = projetDetailView.getZoneSeances();
        List<String> mesSeances = new ArrayList<>(getSeancesTitles());

        for (String title : mesSeances) {
            Button newSeanceButton = createSeanceButton(title);
            int colIndex = gridPane.getChildren().size() % 2; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 2; // Calculating row index
            gridPane.add(newSeanceButton, colIndex, rowIndex);
        }
    }

    private List<String> getSeancesTitles() {
        return new ArrayList<>(this.projectDetailModel.getDisplayedSeances().values());
    }

    private Button createSeanceButton(String title) {
        Button newTaskButton = new Button(title);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./mygroup/src/main/java/Pictures/seance.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }

    private Button createTask(GridPane zoneTaches, String value, Boolean taskEtat, String tacheId) {
        Button cloneButton = createButtonWithIcon("file:Pictures/clone.png");
        Button deleteButton = createButtonWithIcon("file:Pictures/delete.png");
        Button taskButton = new Button();
        CheckBox taskCheckBox = createTaskCheckBox(value, taskEtat);

        configureButtons(zoneTaches, cloneButton, deleteButton, taskButton, taskCheckBox, taskEtat, tacheId);
        setTaskRow(zoneTaches, deleteButton, cloneButton, taskCheckBox, taskButton);
        updateTaskState(taskCheckBox, deleteButton, cloneButton, tacheId, taskEtat);
        configureTaskCheckBoxListener(taskCheckBox, deleteButton, cloneButton, tacheId);

        return taskButton;
    }

    private void configureTaskCheckBoxListener(CheckBox taskCheckBox, Button deleteButton, Button cloneButton,
            String tacheId) {
        taskCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateTaskState(taskCheckBox,
                deleteButton, cloneButton, tacheId, newValue));
    }

    private void updateTaskState(CheckBox taskCheckBox, Button deleteButton, Button cloneButton, String tacheId,
            Boolean taskEtat) {
        if (taskEtat) {
            taskCheckBox.setStyle("-fx-background-color: #FF7E67; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-min-width: 500px; " +
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
                    "-fx-min-width: 500px; " +
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

    private void ServeillerButtons() {
        SurveillerButton(projetDetailView.getListesButton(), "100", "40", "#3F72AF");
        SurveillerButton(projetDetailView.getProjectsButton(), "100", "40", "#3F72AF");
        SurveillerButton(projetDetailView.getArchiveButton(), "100", "40", "#3F72AF");
        SurveillerButton(projetDetailView.getLeftButton(), "150", "40", "#3F72AF");
    }

    public void handleAjouterDocButtonAction() {
        // -----------------------------
    }

    @SuppressWarnings("unused")
    private Button createDocButton(String doc) {
        Button newTaskButton = new Button(doc);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./Pictures/document.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }

    public void getTasksView(ActionEvent event) {

        addTacheview View = new addTacheview(this);
        Stage stage = new Stage();
        View.start(stage);
    }

    public String getProjetId() {
        return this.projectDetailModel.getProjetID();
    }

    private static void configureButtons(GridPane gridPane, Button cloneButton, Button deleteButton, Button taskButton,
            CheckBox taskCheckBox, Boolean isChecked, String tacheId) {
        configureTaskButton(gridPane, taskButton);
    }

    private static void configureTaskButton(GridPane gridPane, Button taskButton) {
        taskButton.setStyle("-fx-background-color: transparent; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 100px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 13px;");
        taskButton.setOnAction(e -> displayMessageDialog());
    }

    private static void displayMessageDialog() {
        // ---------------------------
    }

    private static void setTaskRow(GridPane gridPane, Button deleteButton, Button cloneButton, CheckBox taskCheckBox,
            Button taskButton) {
        int row = gridPane.getRowCount();
        gridPane.add(taskCheckBox, 1, row);
        gridPane.add(taskButton, 1, row);
        GridPane.setHalignment(taskButton, HPos.RIGHT);
    }

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

    private static CheckBox createTaskCheckBox(String taskName, Boolean isChecked) {
        CheckBox taskCheckBox = new CheckBox(taskName);
        taskCheckBox.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 100px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 17px;" +
                "-fx-padding: 0px 0px 0px 5px;");
        taskCheckBox.setAlignment(Pos.CENTER_LEFT);
        if (isChecked != null) {
            taskCheckBox.setSelected(isChecked);
        } else {
            // Default behavior if isChecked is null
            taskCheckBox.setSelected(false); // or true, depending on your application's logic
        }
        return taskCheckBox;
    }

    public void handleAjouterSeanceButton(ActionEvent event) {
        // -----------------------------------------
    }

    private LinkedHashMap<String, String> getTacheMap() {
        System.out.println("Projet ID : " + projectDetailModel.getProjetID());
        LinkedHashMap<String, Boolean> taches = gestionnaireProjet
                .getTaches(projectDetailModel.getProjetID());

        if (taches == null)
            return new LinkedHashMap<>();

        LinkedHashMap<String, String> tacheMap = new LinkedHashMap<>();
        for (String tacheId : taches.keySet()) {
            String tacheTitle = gestionnaireTache.getTitle(tacheId);
            tacheMap.put(tacheId, tacheTitle);
        }
        return tacheMap;
    }

    private LinkedHashMap<String, String> getSeanceMap() {
        List<String> seancesId = gestionnaireProjet.getSeances(projectDetailModel.getProjetID());
        LinkedHashMap<String, String> seances = new LinkedHashMap<>();
        for (String seanceId : seancesId) {
            String seanceTitle = gestionnaireSeance.getTitle(seanceId);
            seances.put(seanceId, seanceTitle);
        }
        return seances;
    }

    public Boolean getTaskEtat(String tacheId) {
        return gestionnaireTache.getTaskEtat(tacheId);
    }

    public void setTaskEtat(String tacheId, Boolean etat) {
        gestionnaireTache.setTaskEtat(tacheId, etat);
        projectDetailModel.addTaskEtat(tacheId, etat);
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

    public String getProjetTitle() {
        return gestionnaireProjet.getProjetTitle(this.getProjetId());
    }

    public String getProjetDescription() {
        return gestionnaireProjet.getProjetDescription(this.getProjetId());
    }

    public String getStartDate() {
        return gestionnaireProjet.getStartDate(this.getProjetId());
    }

    public String getEndDate() {
        return gestionnaireProjet.getEndDate(this.getProjetId());
    }

    public String getCategory() {
        return gestionnaireProjet.getCategory(getProjetId());
    }

    public String getType() {
        return gestionnaireProjet.getType(this.getProjetId());
    }

    public void handleListesButtonAction() {
        Stage stage = (Stage) projetDetailView.getZoneTaches().getScene().getWindow();
        ListeFormView liste = new ListeFormView();
        liste.start(stage);
    }

    public void handleArchiveButtonAction() {
        Stage stage = (Stage) projetDetailView.getZoneTaches().getScene().getWindow();
        ArchiveFormView archive = new ArchiveFormView();
        archive.start(stage);
    }

    public void handleProjectsButtonAction() {
        Stage stage = (Stage) projetDetailView.getZoneTaches().getScene().getWindow();
        ProjetsFormView projets = new ProjetsFormView();
        projets.start(stage);
    }

    public void addTask(String tacheId, String titre) {
        projectDetailModel.addTask(tacheId, titre);
        Button taskButton = createTask(projetDetailView.getZoneTaches(), titre, false, tacheId);
        projetDetailView.getZoneTaches().getChildren().add(taskButton);
    }

}