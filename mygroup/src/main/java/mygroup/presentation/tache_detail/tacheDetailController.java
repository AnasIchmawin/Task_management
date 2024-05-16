package mygroup.presentation.tache_detail;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.persistence.Connexion;
import mygroup.presentation.listes.ListeFormView;

public class tacheDetailController {
    private tacheDetailView sc;
    private tacheDetailModel md;
    private Label titleabel;
    Connexion con;
    GestionnaireTache gt;

    // labels
    Label dateDebutLabel;
    Label dateFinLabel;
    Label categorieLabel;
    Label typeLabel;
    Label descriptionLabel;

    // constructor
    public tacheDetailController(tacheDetailView sc, tacheDetailModel md) {
        gt = new GestionnaireTache();
        titleabel = sc.getTitleabel();
        dateDebutLabel = sc.getDateDebutLabel();
        dateFinLabel = sc.getDateFinLabel();
        categorieLabel = sc.getCategorieLabel();
        typeLabel = sc.getTypeLabel();
        descriptionLabel = sc.getDescriptionLabel();
        BoxHead();
    }

    private void BoxHead() {
        // Box Head
        Document taches = gt.readTask("663bbacc6d8e33b7e243ded3");
        String title = taches.getString("title");
        String dateDebut = taches.getString("dateDebut");
        String dateFin = taches.getString("dateFin");
        String categorie = taches.getString("categorie");
        String type = taches.getString("type");
        String description = taches.getString("description");
        // Titre
        titleabel.setText(title);
        dateDebutLabel.setText(dateDebut);
        dateFinLabel.setText(dateFin);
        categorieLabel.setText(categorie);
        typeLabel.setText(type);
        descriptionLabel.setText(description);
    }

    public void listesButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        ListeFormView listes = new ListeFormView();
        listes.start(stage);
        
    }

}
