package mygroup.presentation.tache_ajoute;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
// import javafx.event.ActionEvent;
// import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mygroup.metier.Gestionnaire.GestionnaireListe;
// import metier.GestionnaireProjet;
// import metier.GestionnaireSeance;
import mygroup.metier.Gestionnaire.GestionnaireTache;
// import metier.POJOProjet;
// import metier.POJOSeance;
import mygroup.metier.POJO.POJOTache;
// import metier.Errors.NonValidList;
import mygroup.presentation.GetDocument.GetDocModel;
import mygroup.presentation.GetTasks.GetTasksView;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.NewList.AddListController;
import mygroup.presentation.NewDocument.AddDocumentController;
// import presentation.GetDocument.GetDocView;
// import presentation.GetDocument.GetDocView;
// import org.bson.Document;
// import presentation.NewProjet.AddProjetModel;
// import presentation.NewProjet.AddProjetView;
import mygroup.presentation.archive.ArchiveFormView;
// import presentation.listes.ListeFormController;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projets.ProjetsFormView;
import mygroup.presentation.taches.TachesFormController;

// import presentation.seance_ajoute.SceanceAjouteView;
// import presentation.archive.ArchiveFormController;
// import presentation.archive.ArchiveFormView;
// import presentation.listes.ListeFormView;
// import presentation.projets.ProjetsFormView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.SystemDefaultCredentialsProvider;

@SuppressWarnings("unused")
public class ControllerFromTacheAjout {
    private GestionnaireTache gestionnaireTache;
    private TachesFormController tachesFormController;
    private GestionnaireListe gestionnaireListe;
    private addTacheview addTacheview;
    private GetDocModel model;
    private ModuleFromTacheAjout moduleFromTacheAjout;
    private AddListController addListController;

    public ControllerFromTacheAjout(addTacheview addTacheview) {
        this.addTacheview = addTacheview;
        this.model = new GetDocModel();
        this.gestionnaireTache = new GestionnaireTache();
        this.moduleFromTacheAjout = new ModuleFromTacheAjout("");
        this.gestionnaireListe = new GestionnaireListe();

    }

    public ControllerFromTacheAjout(addTacheview addTacheview, TachesFormController tachesFormController) {
        this.tachesFormController = tachesFormController;
        this.addTacheview = addTacheview;
        this.model = new GetDocModel();
        this.moduleFromTacheAjout = new ModuleFromTacheAjout(tachesFormController.getListId());
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireListe = new GestionnaireListe();

    }

    public ControllerFromTacheAjout(addTacheview addTacheview, AddListController addListController) {
        this.addListController = addListController;
        this.addTacheview = addTacheview;
        this.model = new GetDocModel();
        this.gestionnaireTache = new GestionnaireTache();
        this.gestionnaireListe = new GestionnaireListe();

    }

    public void handleListesButton() {
        ListeFormView listeFormView = new ListeFormView();
        Stage stage = this.addTacheview.getStage();
        listeFormView.start(stage);
    }

    public void handleProjetsButton() {
        ProjetsFormView projets = new ProjetsFormView();
        Stage stage = this.addTacheview.getStage();
        projets.start(stage);
    }

    public void handleArchiveButton() {
        ArchiveFormView archive = new ArchiveFormView();
        Stage stage = this.addTacheview.getStage();
        archive.start(stage);
    }

    public void handleAjouterButtonAction() {
        System.out.println("Ajouter Button Clicked");
        AddDocumentView view = new AddDocumentView(this);
        Stage stage = new Stage();
        view.start(stage);
    }

    public void addBlurEffect() {
        BoxBlur blur = new BoxBlur(5, 5, 1);
        this.addTacheview.getRoot().setEffect(blur);
    }

    public void removeBlurEffect() {
        this.addTacheview.getRoot().setEffect(null);
    }

    // mod

    public void addDocToTache(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to Tache: " + doc);
        displayDocuments();
    }

    public void displayDocuments() {
        List<String> mesDocs = new ArrayList<>(this.model.getListOfDocuments().values());

        this.addTacheview.getZoneDocuments().getChildren().clear();

        for (String doc : mesDocs) {
            Button newTaskButton = createDocButton(doc);
            int colIndex = this.addTacheview.getZoneDocuments().getChildren().size() % 6;

            int rowIndex = this.addTacheview.getZoneDocuments().getChildren().size() / 6;
            this.addTacheview.getZoneDocuments().add(newTaskButton, colIndex, rowIndex);
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

    public void handleSauvegarderButtonAction() {
        try {
            String titre = this.addTacheview.gettitre();
            String dateDebut = this.addTacheview.getDateDebut();
            String TempsDebut = this.addTacheview.getTempsDebut();
            String dateFin = this.addTacheview.getDateFin();
            String TempsFin = this.addTacheview.getTempsFin();
            String description = this.addTacheview.getDescription();
            String categorie = this.addTacheview.getCategorie();
            List<String> IdsDoc = this.model.getListOfDocuments().keySet().stream().toList();
            Boolean etat = false; // Assuming false as default
            String projet = ""; // Empty string as default
            String liste = ""; // Empty string as default
            if (this.moduleFromTacheAjout != null) {
                liste = moduleFromTacheAjout.getIdListe();
            }
            POJOTache tache = new POJOTache(titre, etat, categorie, description, dateDebut, TempsDebut, dateFin,
                    TempsFin,
                    IdsDoc, projet, liste);
            this.gestionnaireTache.setTache(tache);
            this.gestionnaireTache.createTache();
            String tacheId = this.gestionnaireTache.getLastTacheId();
            if (liste != "") {
                gestionnaireListe.setTacheToListe(liste, tacheId);
            }
            alert("Tache créée", "La tache a été créée avec succès");
            // get stage et close
            if (this.addListController != null) {
                System.out.println("id" + tacheId + "titre" + titre);
                addListController.addNewTask(tacheId, titre);
            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la tache : les champs ne sont pas valides");
            alert.showAndWait();
            // stack
            System.out.println(e.getMessage());
        } finally {
            this.addListController.displayTasks();
            closerWindow();
        }
    }
    // public void handleUpdateButtonAction() {
    // try {

    // String titre = this.addTacheview.gettitre();
    // String dateDebut = this.addTacheview.getDateDebut();
    // String TempsDebut = this.addTacheview.getTempsDebut();
    // String dateFin = this.addTacheview.getDateFin();
    // String TempsFin = this.addTacheview.getTempsFin();
    // String description = this.addTacheview.getDescription();
    // String categorie = this.addTacheview.getCategorie();
    // List<String> IdsDoc =
    // this.model.getListOfDocuments().keySet().stream().toList();
    // Boolean etat = false; // Assuming false as default
    // String projet = ""; // Empty string as default
    // String liste = ""; // Empty string as default

    // POJOTache tache = new POJOTache(titre, etat,categorie, description,
    // dateDebut, TempsDebut , dateFin, TempsFin,
    // IdsDoc, projet , liste);
    // this.gestionnaireTache.setTache(tache);
    // this.gestionnaireTache.updateTask();
    // alert("Tache créée", "La tache a été créée avec succès");

    // } catch (Exception e) {
    // Alert alert = new Alert(AlertType.ERROR);
    // alert.setTitle("Erreur");
    // alert.setHeaderText("Erreur lors de la création de la tache : les champs ne
    // sont pas valides");
    // alert.showAndWait();
    // }
    // }

    public void closerWindow() {
        Stage stage = this.addTacheview.getStage();
        stage.close();
    }

    public void alert(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> alert.close());
        delay.play();
    }

    public void setIdTitreDocument(String id, String titre) {
        this.model.setIdTitreDocument(id, titre);
    }

    public void handleImportButtonAction(ActionEvent event) {
        GetTasksView view = new GetTasksView(addTacheview.getAddListController(),this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        view.start(stage);

    }

}
