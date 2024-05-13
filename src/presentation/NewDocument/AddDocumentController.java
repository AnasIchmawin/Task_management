package presentation.NewDocument;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import metier.POJODocument;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.taches.TachesFormController;
import metier.GestionnaireDocument;

public class AddDocumentController {
    private GestionnaireDocument gestionnaireDocument;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private AddDocumentView view;

    public AddDocumentController(AddDocumentView view) {
        this.view = view;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view, ControllerFromTacheAjout controllerFromTacheAjout) {
        this.view = view;
        this.controllerFromTacheAjout = controllerFromTacheAjout;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public void saveDocument() {
        LocalDateTime dateTime = LocalDateTime.now();
        String titre = view.getTitreField();
        String url = view.getURL();
        String desc = view.getDescription();
        view.setDateInsertion(dateTime.toLocalDate());
        String dateInsertion = view.getDateInsertion();
        String idProjet = view.getIdProjet();
        String idTache = view.getIdTache();
        String idSeance = view.getIdSeance();
        System.out.println("Titre : " + titre);
        System.out.println("URL : " + url);
        System.out.println("Description : " + desc);
        System.out.println("Date d'insertion : " + dateInsertion);
        System.out.println("ID Projet : " + idProjet);
        System.out.println("ID Tache : " + idTache);
        System.out.println("ID Seance : " + idSeance);
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Document enregistré avec succès !");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    view.close(); // Ferme la fenêtre
                }
            });
        });
        POJODocument document = new POJODocument(titre, url, desc, dateInsertion, idProjet, idTache, idSeance);
        this.gestionnaireDocument.setPojoDocument(document);
        this.gestionnaireDocument.creerDocument();
        controllerFromTacheAjout.addDocToTache(this.gestionnaireDocument.getIdLastDoc(),titre);
    }

    public void close() {
        Platform.exit();
    }
}
