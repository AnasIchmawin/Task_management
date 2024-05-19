package mygroup.presentation.seance_ajoute;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.metier.POJO.POJOSeance;
import mygroup.presentation.GetDocument.GetDocModel;
import mygroup.presentation.GetSeanceFromCalendar.GetSeanceFromCalendar;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.NewProjet.AddProjetController;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.projet_detail.ProjetDetailController;
import mygroup.presentation.projets.ProjetsFormView;

public class SceanceAjouteController {
    private SceanceAjouteView seanceAjouteView;
    private GetDocModel model;
    private GestionnaireSeance gestionnaireSeance;
    private AddProjetController addProjetController;
    private String dateSeanceFormated;
    private ProjetDetailController projetDetailController;

    public SceanceAjouteController(SceanceAjouteView seanceAjouteView, AddProjetController addProjetController) {
        this.seanceAjouteView = seanceAjouteView;
        this.addProjetController = addProjetController;
        this.model = new GetDocModel();
        this.gestionnaireSeance = new GestionnaireSeance();
    }




    public void handleListesButton() {
        ListeFormView listeFormView = new ListeFormView();
        Stage stage = this.seanceAjouteView.getStage();
        listeFormView.start(stage);
    }

    public void handleProjetsButton() {
        ProjetsFormView projets = new ProjetsFormView();
        Stage stage = this.seanceAjouteView.getStage();
        projets.start(stage);
    }

    public void handleArchiveButton() {
        ArchiveFormView archive = new ArchiveFormView();
        Stage stage = this.seanceAjouteView.getStage();
        archive.start(stage);
    }

    public void handleAjouterButtonAction() {
        AddDocumentView addDocumentView = new AddDocumentView(this);
        Stage stage = new Stage();
        addDocumentView.start(stage);
    }

    public void addDocToSeance(String id, String doc) {
        this.model.addDocumentToSeance(id, doc);
        System.out.println("Document added to seance: " + doc);
    }

    public void displayDocuments() {
        this.seanceAjouteView.getZoneDocuments().getChildren().clear();
        List<String> mesDocs = new ArrayList<>(this.model.getListOfDocuments().values());

        for (String doc : mesDocs) {
            Button newTaskButton = createDocButton(doc);
            int colIndex = this.seanceAjouteView.getZoneDocuments().getChildren().size() % 6; // Calculating column
                                                                                              // index
            int rowIndex = this.seanceAjouteView.getZoneDocuments().getChildren().size() / 6; // Calculating row index
            this.seanceAjouteView.getZoneDocuments().add(newTaskButton, colIndex, rowIndex);
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
            String titre = this.seanceAjouteView.getTitre();
            String dateDebut = this.seanceAjouteView.getDateDebut();
            String heureDebut = this.seanceAjouteView.getTimeDebut();
            String dateFin = this.seanceAjouteView.getDateFin();
            String heureFin = this.seanceAjouteView.getTimeFin();
            String description = this.seanceAjouteView.getDescription();
            String note = this.seanceAjouteView.getZoneNote();
            List<String> IdsDoc = this.model.getListOfDocuments().keySet().stream().toList();

            POJOSeance seance = new POJOSeance(titre, dateDebut, heureDebut, dateFin, heureFin,
                    description, note, IdsDoc);
            this.gestionnaireSeance.setSeance(seance);
            this.gestionnaireSeance.createSeance();
            this.addProjetController.setSeance(gestionnaireSeance.getLastSeance());
            this.addProjetController.displaySeances();
            alert("Séance créée", "La séance a été créée avec succès");
            seanceAjouteView.close();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la séance : les champs ne sont pas valides");
            alert.showAndWait();
        }
    }

    public void alert(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();

        // Créer une pause de 1 secondes avant de fermer l'alerte
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> alert.close()); // Utilise close() pour fermer l'alerte
        delay.play();
    }

    public String getDateSeanceFormated() {
        return dateSeanceFormated;
    }

    public void setDateSeanceFormated(String dateSeanceFormated) {
        this.dateSeanceFormated = dateSeanceFormated;
    }

    public void handleConfirmerButtonAction() {
        try {
            String dateSeance = this.seanceAjouteView.getDateSeance();
            // convert format to DD/MM/YYYY
            System.out.println("Date Seance: " + dateSeance);
            String[] date = dateSeance.split("-");
            this.setDateSeanceFormated(date[2] + "/" + date[1] + "/" + date[0]);
            GetSeanceFromCalendar GetSeanceFromCalendar = new GetSeanceFromCalendar(this, addProjetController);
            GetSeanceFromCalendar.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Veuillez saisir une date valide");
            alert.showAndWait();
        }

    }

    public void closeWindow() {
        this.seanceAjouteView.close();
    }
}
