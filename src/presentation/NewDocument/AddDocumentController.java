package presentation.NewDocument;

import java.time.LocalDateTime;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import metier.POJO.POJODocument;
import presentation.projet_detail.ProjetDetailController;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.taches.TachesFormController;
import metier.Gestionnaire.GestionnaireDocument;

@SuppressWarnings("unused")
public class AddDocumentController {
    private GestionnaireDocument gestionnaireDocument;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private AddDocumentView view;
    @SuppressWarnings("unused")
    private ProjetDetailController controleur;

    public AddDocumentController(AddDocumentView view) {
        this.view = view;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view, ProjetDetailController controleur) {
        this.view = view;
        this.controleur = controleur;
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
    
        // Vérifiez si l'URL est accessible
        if (gestionnaireDocument.isUrlAccessible(url)) {
            System.out.println("L'URL est accessible.");
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
            controllerFromTacheAjout.addDocToTache(this.gestionnaireDocument.getIdLastDoc(), titre);
        } else {
            System.out.println("L'URL n'est pas accessible.");
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'URL fournie n'est pas accessible. Veuillez vérifier l'URL et réessayer.");
                alert.showAndWait();
            });
        }
    }
    

    public void close() {
        Platform.exit();
    }
}
