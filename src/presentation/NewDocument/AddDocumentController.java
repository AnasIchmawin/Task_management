package presentation.NewDocument;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import metier.POJODocument;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.tache_detail.tacheDetailController;
import metier.GestionnaireDocument;
import presentation.seance_ajoute.SceanceAjouteController;

public class AddDocumentController {
    private GestionnaireDocument gestionnaireDocument;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private tacheDetailController controllerFromTacheDetail;
    private AddDocumentView view;

    public AddDocumentController(AddDocumentView view, ControllerFromTacheAjout controllerFromTacheAjout) {
        this.view = view;
        this.controllerFromTacheAjout = controllerFromTacheAjout;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view) {
        this.view = view;
        gestionnaireDocument = new GestionnaireDocument();
    }

    // public void saveDocumentFromTacheDetail() {
    //     String titre = view.getTitreField();
    //     String url = view.getURL();
    //     String desc = view.getDescription();
    
    //     // Vérifiez si l'URL est accessible
    //     if (gestionnaireDocument.isUrlAccessible(url)) {
    //         System.out.println("L'URL est accessible.");
    //         BienEnregistre();
    //         POJODocument document = new POJODocument(titre, desc, url);
    //         this.gestionnaireDocument.setPojoDocument(document);
    //         this.gestionnaireDocument.creerDocument();
    //         controllerFromTacheDetail.addDocToTache(this.gestionnaireDocument.getIdLastDoc(), titre);
    //     } else {
    //         NonEnregistre();
    //     }
    // }

    public void saveDocumentFromeTacheAjout() {
        String titre = view.getTitreField();
        String url = view.getURL();
        String desc = view.getDescription();
    
        // Vérifiez si l'URL est accessible
        if (gestionnaireDocument.isUrlAccessible(url)) {
            System.out.println("L'URL est accessible.");
            BienEnregistre();
            POJODocument document = new POJODocument(titre, desc, url);
            this.gestionnaireDocument.setPojoDocument(document);
            this.gestionnaireDocument.creerDocument();
            controllerFromTacheAjout.addDocToTache(this.gestionnaireDocument.getIdLastDoc(), titre);
        } else {
            NonEnregistre();
        }
    }

    // public void saveDocumentFromeSeanceAjout(){
    //     String titre = view.getTitreField();
    //     String url = view.getURL();
    //     String desc = view.getDescription();
    
    //     // Vérifiez si l'URL est accessible
    //     if (gestionnaireDocument.isUrlAccessible(url)) {
    //         System.out.println("L'URL est accessible.");
    //         BienEnregistre();
    //         POJODocument document = new POJODocument(titre, desc, url);
    //         this.gestionnaireDocument.setPojoDocument(document);
    //         this.gestionnaireDocument.creerDocument();
    //         SceanceAjouteController.addDocToSeance(this.gestionnaireDocument.getIdLastDoc(), titre);
    //     } else {
    //         NonEnregistre();
    //     }
    // }



    public void BienEnregistre() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le document a été enregistré avec succès.");
            alert.showAndWait();
            //close the window when the button ok is clicked
            if (alert.getResult() == ButtonType.OK) {
                view.close();
            }
        });
    }

    public void NonEnregistre() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le document n'a pas été enregistré.");
            alert.showAndWait();
        });
    }
    

    public void close() {
        Platform.exit();
    }
}
