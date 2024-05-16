package presentation.NewDocument;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import metier.Gestionnaire.GestionnaireDocument;
import metier.POJO.POJODocument;
import presentation.projet_detail.ProjetDetailController;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.tache_detail.tacheDetailController;
import presentation.seance_ajoute.SceanceAjouteController;
import presentation.NewProjet.AddProjetController;

@SuppressWarnings("unused")
public class AddDocumentController {
    private AddDocumentView view;
    private GestionnaireDocument gestionnaireDocument;
    private ControllerFromTacheAjout controllerFromTacheAjout;
    private tacheDetailController controllerFromTacheDetail;
    private SceanceAjouteController SceanceAjouteController;
    private AddProjetController addProjetController;

    public AddDocumentController(AddDocumentView view, ControllerFromTacheAjout controllerFromTacheAjout) {
        this.view = view;
        this.controllerFromTacheAjout = controllerFromTacheAjout;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view, tacheDetailController controllerFromTacheDetail) {
        this.view = view;
        this.controllerFromTacheDetail = controllerFromTacheDetail;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view, SceanceAjouteController SceanceAjouteController) {
        this.view = view;
        this.SceanceAjouteController = SceanceAjouteController;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public AddDocumentController(AddDocumentView view, AddProjetController addProjetController) {
        this.view = view;
        this.addProjetController = addProjetController;
        gestionnaireDocument = new GestionnaireDocument();
    }

    public void saveDocumentFromTacheDetail() {
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
            // controllerFromTacheDetail.addDocToTache(this.gestionnaireDocument.getIdLastDoc(), titre);
        } else {
            NonEnregistre();
        }
    }

    public void saveDocumentFromProjet() {
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
            // addProjetController.addDocToProjet(this.gestionnaireDocument.getIdLastDoc(), titre);
        } else {
            NonEnregistre();
        }
    }

    public void saveDocumentFromeTacheAjout() {
        System.out.println("button clicked in control");
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

    public void saveDocumentFromeSeanceAjout(){
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
            SceanceAjouteController.addDocToSeance(this.gestionnaireDocument.getIdLastDoc(), titre);
        } else {
            NonEnregistre();
        }
    }



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
