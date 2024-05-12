package presentation.tache_ajoute;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
// import javafx.event.ActionEvent;
// import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
// import metier.GestionnaireProjet;
// import metier.GestionnaireSeance;
import metier.GestionnaireTache;
// import metier.POJOProjet;
// import metier.POJOSeance;
import metier.POJOTache;
// import metier.Errors.NonValidList;
import presentation.GetDocument.GetDocModel;
import presentation.GetDocument.GetDocView;
// import presentation.GetDocument.GetDocView;
// import presentation.GetDocument.GetDocView;
// import org.bson.Document;
// import presentation.NewProjet.AddProjetModel;
// import presentation.NewProjet.AddProjetView;
import presentation.archive.ArchiveFormView;
// import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.projets.ProjetsFormView;
// import presentation.seance_ajoute.SceanceAjouteView;
// import presentation.archive.ArchiveFormController;
// import presentation.archive.ArchiveFormView;
// import presentation.listes.ListeFormView;
// import presentation.projets.ProjetsFormView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControllerFromTacheAjout {
    private GestionnaireTache gestionnaireTache;
    private addTacheview addTacheview;
    private GetDocModel model;


    public ControllerFromTacheAjout(addTacheview addTacheview) {
        this.addTacheview = addTacheview;
        this.model = new GetDocModel();
        this.gestionnaireTache = new GestionnaireTache();

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
        GetDocView view = new GetDocView(this);
        Stage stage = new Stage();
        view.start(stage);
    }

    

    //mod
    
    public void addDocToTache(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to Tache: " + doc);
    }

    public void displayDocuments() {
        List<String> mesDocs = new ArrayList<>(this.model.getListOfDocuments().values());

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

    public void handleSauvegarderButtonAction()  {
        try {
                
                String titre = this.addTacheview.gettitre();
                String dateDebut = this.addTacheview.getDateDebut();
                String TempsDebut = this.addTacheview.getTempsDebut();
                String dateFin = this.addTacheview.getDateFin();
                String TempsFin = this.addTacheview.getTempsFin();
                String description = this.addTacheview.getDescription();
                String categorie = this.addTacheview.getCategorie();
                List<String> IdsDoc = this.model.getListOfDocuments().keySet().stream().toList();
                Boolean etat = false;  // Assuming false as default
                String projet = "";    // Empty string as default
                String liste = "";     // Empty string as default
                
                
            POJOTache tache = new POJOTache(titre, etat,categorie, description, dateDebut, TempsDebut , dateFin, TempsFin,
                IdsDoc, projet , liste);
            this.gestionnaireTache.setTache(tache);
            this.gestionnaireTache.createTache();
            alert("Tache créée", "La tache a été créée avec succès");

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la tache : les champs ne sont pas valides");
            alert.showAndWait();
        }
    }
    public void handleUpdateButtonAction() {
        try {
                
                String titre = this.addTacheview.gettitre();
                String dateDebut = this.addTacheview.getDateDebut();
                String TempsDebut = this.addTacheview.getTempsDebut();
                String dateFin = this.addTacheview.getDateFin();
                String TempsFin = this.addTacheview.getTempsFin();
                String description = this.addTacheview.getDescription();
                String categorie = this.addTacheview.getCategorie();
                List<String> IdsDoc = this.model.getListOfDocuments().keySet().stream().toList();
                Boolean etat = false;  // Assuming false as default
                String projet = "";    // Empty string as default
                String liste = "";     // Empty string as default
                
                
            POJOTache tache = new POJOTache(titre, etat,categorie, description, dateDebut, TempsDebut , dateFin, TempsFin,
                IdsDoc, projet , liste);
            this.gestionnaireTache.setTache(tache);
            this.gestionnaireTache.updateTask();
            alert("Tache créée", "La tache a été créée avec succès");

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la tache : les champs ne sont pas valides");
            alert.showAndWait();
        }
    }

       public void closerWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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



}


    
