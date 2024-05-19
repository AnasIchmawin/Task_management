package mygroup.presentation.NewProjet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireDocument;
import mygroup.metier.Gestionnaire.GestionnaireProjet;
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.metier.POJO.POJOProjet;
import mygroup.metier.Errors.NonValidList;
import mygroup.presentation.NewDocument.AddDocumentController;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.seance_ajoute.SceanceAjouteView;
import mygroup.presentation.tache_ajoute.addTacheview;

public class AddProjetController {
    private GestionnaireProjet gestionnaireProjet;
    private GestionnaireTache gestionnaireTache;
    private GestionnaireDocument gestionnaireDocument;
    private GestionnaireSeance gestionnaireSeance;
    private AddDocumentController addDocumentController;
    private AddProjetView addProjetView;
    private AddProjetModel addProjetModel;

    public AddProjetController(AddProjetView addProjetView) {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireDocument = new GestionnaireDocument();
        this.gestionnaireSeance = new GestionnaireSeance();
        this.addProjetView = addProjetView;
        this.addProjetModel = new AddProjetModel(new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>());
    }

    public void saveInfosProjet(ActionEvent event) {
        if (!addProjetView.getTitre().isEmpty()) {

            POJOProjet nouveauProjet = new POJOProjet();
            nouveauProjet.setTitre(addProjetView.getTitre());
            nouveauProjet.setDescription(addProjetView.getDescription());
            nouveauProjet.setDateDebut(addProjetView.getDateDebut());
            nouveauProjet.setDateFin(addProjetView.getDateFin());
            nouveauProjet.setCategorie(addProjetView.getCategorie());
            nouveauProjet.setType(addProjetView.getType());

            nouveauProjet.setTaches(getTachesIds());
            nouveauProjet.setDocuments(getDocumentsIds());
            nouveauProjet.setSeances(getSeancesIds());
            this.gestionnaireProjet.setProjet(nouveauProjet);

            try {
                gestionnaireProjet.creerProjet();
                this.gestionnaireTache.setProjetId(getTachesIds(), getLastProjetId());
                this.gestionnaireDocument.setProjetId(getDocumentsIds(), getLastProjetId());
                this.gestionnaireSeance.setProjetId(getSeancesIds(), getLastProjetId());
            } catch (NonValidList e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
            }
        }
    }

    public String getLastProjetId() {
        return gestionnaireProjet.getLastProjetId();
    }

    private List<String> getSeancesIds() {
        return new ArrayList<>(this.addProjetModel.getSeancesSelectionnees().keySet());
    }

    private List<String> getDocumentsIds() {
        return new ArrayList<>(this.addProjetModel.getDocumentsSelectionnes().keySet());
    }

    private List<String> getTachesIds() {
        return new ArrayList<>(this.addProjetModel.getTachesSelectionnees().keySet());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleAjouterTacheButton(ActionEvent event) {
        addTacheview addTacheview = new addTacheview(this);
        Stage stage = new Stage();
        addTacheview.start(stage);
    }

    public void displayTasks() {
        System.out.println("displayTasks");
        clearTasks();
        List<String> mesTaches = new ArrayList<>(getTachesTitles());

        for (String title : mesTaches) {
            Button newTaskButton = createTaskButton(title);
            int colIndex = addProjetView.getZoneTaches().getChildren().size() % 2; // Calculating column index
            int rowIndex = addProjetView.getZoneTaches().getChildren().size() / 2; // Calculating row index
            addProjetView.getZoneTaches().add(newTaskButton, colIndex, rowIndex);
        }
    }

    private void clearTasks() {
        addProjetView.getZoneTaches().getChildren().clear();
    }

    public void displayDocuments() {
        clearDocuments();
        List<String> mesDocuments = new ArrayList<>(getDocumentsTitles());

        for (String title : mesDocuments) {
            Button newDocumentButton = createDocumentButton(title);
            int colIndex = addProjetView.getZoneDocuments().getChildren().size() % 2; // Calculating column index
            int rowIndex = addProjetView.getZoneDocuments().getChildren().size() / 2; // Calculating row index
            addProjetView.getZoneDocuments().add(newDocumentButton, colIndex, rowIndex);
        }
    }

    private void clearDocuments() {
        addProjetView.getZoneDocuments().getChildren().clear();
    }

    public void displaySeances() {
        clearSeances();
        GridPane gridPane = addProjetView.getZoneSeances();
        List<String> mesSeances = new ArrayList<>(getSeancesTitles());

        for (String title : mesSeances) {
            Button newSeanceButton = createSeanceButton(title);
            int colIndex = gridPane.getChildren().size() % 2; // Calculating column index
            int rowIndex = gridPane.getChildren().size() / 2; // Calculating row index
            gridPane.add(newSeanceButton, colIndex, rowIndex);
        }
    }

    private List<String> getTachesTitles() {
        return new ArrayList<>(this.addProjetModel.getTachesSelectionnees().values());
    }

    private List<String> getDocumentsTitles() {
        return new ArrayList<>(this.addProjetModel.getDocumentsSelectionnes().values());
    }

    private List<String> getSeancesTitles() {
        return new ArrayList<>(this.addProjetModel.getSeancesSelectionnees().values());
    }

    public void addSeance(LinkedHashMap<String, String> seance) {
        this.addProjetModel.addSeance(seance);
    }

    public void addDocument(LinkedHashMap<String, String> document) {
        this.addProjetModel.addDocument(document);
    }

    public AddProjetModel getAddProjetModel() {
        return this.addProjetModel;
    }

    // addTaskToList
    public void addTask(LinkedHashMap<String, String> task) {
        this.addProjetModel.addTask(task);
    }

    // closerWindow
    public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private Button createTaskButton(String title) {
        Button newTaskButton = new Button(title);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./mygroup/src/main/java/Pictures/to-do.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
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

    private Button createDocumentButton(String title) {
        Button newTaskButton = new Button(title);
        newTaskButton.setStyle("-fx-background-color: #112D4E; " +
                "-fx-background-radius: 10px; " +
                "-fx-min-width: 50px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 18px;");

        try {
            Image listIcon = new Image("file:./mygroup/src/main/java/Pictures/document.png");
            ImageView listIconView = new ImageView(listIcon);
            listIconView.setFitWidth(15);
            listIconView.setFitHeight(15);
            newTaskButton.setGraphic(listIconView);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'icône de la liste : " + e.getMessage());
        }

        return newTaskButton;
    }

    public void handleAjouterDocumentButton(ActionEvent event) {
        AddDocumentView addDocumentView = new AddDocumentView(this);
        Stage stage = new Stage();
        addDocumentView.start(stage);
    }

    public void handleAjouterSeanceButton(ActionEvent event) {
        SceanceAjouteView sceanceAjouteView = new SceanceAjouteView(this);
        Stage stage = new Stage();
        sceanceAjouteView.start(stage);
    }

    public void setSeance(LinkedHashMap<String, String> Seance) {
        this.addProjetModel.addSeance(Seance);
    }

    public void clearSeances() {
        addProjetView.getZoneSeances().getChildren().clear();
    }

}
