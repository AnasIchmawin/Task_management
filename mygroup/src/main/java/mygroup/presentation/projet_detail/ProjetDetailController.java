package mygroup.presentation.projet_detail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireDocument;
import mygroup.metier.Gestionnaire.GestionnaireProjet;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.persistence.DAO.DAOProjet;
import mygroup.presentation.GetDocument.GetDocModel;
import mygroup.presentation.tache_ajoute.addTacheview;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.seance_ajoute.SceanceAjouteView;
import mygroup.presentation.taches.TachesFormController;
import mygroup.presentation.taches.TachesFormModel;
import mygroup.presentation.taches.TachesFormView;
import mygroup.presentation.seance_ajoute.SceanceAjouteView;


public class ProjetDetailController {
    private GestionnaireDocument gestionnaireDocument;
    private GestionnaireProjet gestionnaireProjet;
    private static GestionnaireTache gestionnaireTache;
    private static TachesFormModel tacheModel;
    private ProjectDetail projectDetail;
    private GetDocModel model;
    private Projet_Detail_View view;

    public ProjetDetailController(Projet_Detail_View view){
        this.gestionnaireProjet = new GestionnaireProjet();
        this.gestionnaireTache = new GestionnaireTache();
        this.model = new GetDocModel();
        this.view = view;

        this.gestionnaireDocument = new GestionnaireDocument();
        projectDetail = new ProjectDetail(
                "Project Title",
                "Project Description",
                "2024-01-01",
                "2024-12-31",
                "Education",
                "Research"
        );
        this.tacheModel = new TachesFormModel(getTacheMap());
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
        this.view.settitle(getProjetTitle());
        this.view.setDescription(getProjetDescription());
        this.view.setDateDebut(getStartDate());
        this.view.setDateFin(getEndDate());
        this.view.setCategorie(getCategory());
        this.view.setType(getType());
        this.displayedTasks(true);
        this.ServeillerButtons();
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
        return gestionnaireProjet.getProjetTitle(this.getProjetId());
    }

    public String getType() {
        return gestionnaireProjet.getType(this.getProjetId());
    }

    private void ServeillerButtons() {
        SurveillerButton(view.getListesButton(), "100", "40", "#3F72AF");
        SurveillerButton(view.getProjectsButton(), "100", "40", "#3F72AF");
        SurveillerButton(view.getArchiveButton(), "100", "40", "#3F72AF");
        SurveillerButton(view.getLeftButton(), "150", "40", "#3F72AF");
    }
    public void handleAjouterDocButtonAction() {
        System.out.println("Ajouter Button Clicked");
        AddDocumentView adddoc = new AddDocumentView(this);
        Stage stage = new Stage();
        adddoc.start(stage);
    }

    public void addDocToTache(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to Projet: " + doc);
        displayDocuments();
    }

    public void displayDocuments() {
        List<String> mesDocs = new ArrayList<>(this.model.getListOfDocuments().values());
        this.view.getZoneDocuments().getChildren().clear();

        for (String doc : mesDocs) {
            Button newTaskButton = createDocButton(doc);
            int colIndex = this.view.getZoneDocuments().getChildren().size() % 6; 
                                                                                              
            int rowIndex = this.view.getZoneDocuments().getChildren().size() / 6; 
            this.view.getZoneDocuments().add(newTaskButton, colIndex, rowIndex);
        }
    }
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

    public void getTasksView(ActionEvent event){

        addTacheview View = new addTacheview(this);
        Stage stage = new Stage();
                View.start(stage);
    }

    public String getProjetId() {
        return this.model.getIdProjet();
    }

    public void displayedTasks(boolean isSorted) {
        tacheModel.setDisplayedTasks(getTacheMap());
        if (isSorted) {
            tacheModel.sortTasksByTitle();
        }
        view.getZoneTaches().getChildren().clear();
        int colCount = 0;
        int rowCount = 0;

        for (Map.Entry<String, String> entry : tacheModel.getDisplayedTasks().entrySet()) {
            createTask(view.getZoneTaches(), entry.getValue(), getTaskEtat(entry.getKey()), entry.getKey());
            // tacheModel.addGridInfosCase(List.of(rowCount, colCount), entry.getKey());

            if (++colCount == 3) {
                colCount = 0;
                rowCount++;
            }
        }
    }

    private static void configureButtons(GridPane gridPane, Button cloneButton, Button deleteButton, Button taskButton,
            CheckBox taskCheckBox, Boolean isChecked, String tacheId) {
        configureDeleteButton(gridPane, deleteButton, cloneButton, taskCheckBox, tacheId);
        configureCloneButton(gridPane, cloneButton, tacheId);
        configureTaskButton(gridPane, taskButton);
    }

    private static void configureDeleteButton(GridPane gridPane, Button deleteButton, Button cloneButton,
           CheckBox taskCheckBox, String tacheId) {
        // deleteButton.setOnAction(e -> {
        //     removeTask(gridPane, taskCheckBox, deleteButton, cloneButton);
        //     gestionnaireTache.deleteTask(tacheId);
        //     gestionnaireProjet.deleteTacheFromProjet(this.getProjetId(), tacheId);
        // });
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

    private static void configureTaskCheckBoxListener(CheckBox taskCheckBox, Button deleteButton, Button cloneButton,
            String tacheId) {
        taskCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateTaskState(taskCheckBox,
                deleteButton, cloneButton, tacheId, newValue));
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

    private static void setTaskRow(GridPane gridPane, Button deleteButton, Button cloneButton, CheckBox taskCheckBox,
            Button taskButton) {
        int row = gridPane.getRowCount();
        gridPane.add(deleteButton, 3, row);
        gridPane.add(cloneButton, 4, row);
        gridPane.add(taskCheckBox, 5, row);
        gridPane.add(taskButton, 5, row);
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
                "-fx-min-width: 700px; " +
                "-fx-min-height: 30px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 17px;" +
                "-fx-padding: 0px 0px 0px 5px;");
        taskCheckBox.setAlignment(Pos.CENTER_LEFT);
        taskCheckBox.setSelected(isChecked);
        return taskCheckBox;
    }

    public void handleAjouterSeanceButton(ActionEvent event) {
        SceanceAjouteView sceanceAjouteView = new SceanceAjouteView(this);
        Stage stage = new Stage();
        sceanceAjouteView.start(stage);
    }


    private LinkedHashMap<String, String> getTacheMap() {
        LinkedHashMap<String, Boolean> taches = gestionnaireProjet.getTaches(this.getProjetId());

        LinkedHashMap<String, String> tacheMap = new LinkedHashMap<>();
        for (String tacheId : taches.keySet()) {
            String tacheTitle = gestionnaireTache.getTitle(tacheId);
            tacheMap.put(tacheId, tacheTitle);
        }
        return tacheMap;
    }

    public Boolean getTaskEtat(String tacheId) {
        return gestionnaireTache.getTaskEtat(tacheId);
    }

    public static void setTaskEtat(String tacheId, Boolean etat) {
        gestionnaireTache.setTaskEtat(tacheId, etat);
        tacheModel.addTaskEtat(tacheId, etat);
    }



    private static void configureCloneButton(GridPane gridPane, Button cloneButton, String tacheId) {
        // cloneButton.setOnAction(e -> {
        //     createTask(gridPane, getnameTask(tacheId), tacheModel.getTaskEtat(tacheId), tacheId);
        //     gestionnaireTache.cloneTask(tacheId);
        //     gestionnaireProjet.setTacheToProjet(this.getProjetId(), gestionnaireTache.getLastTacheId());
        // });
    }

    public void setIdTitreDocument(String id, String titre) {
        this.model.setIdTitreDocument(id, titre);
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