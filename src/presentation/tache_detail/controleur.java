package presentation.tache_detail;

import org.bson.Document;

import javafx.scene.control.Label;
import metier.Gestionnaire.GestionnaireTache;
import persistence.Connexion;

public class controleur {
    @SuppressWarnings("unused")
    private screen sc;
    @SuppressWarnings("unused")
    private tacheDetailModele md;
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
    public controleur(screen sc, tacheDetailModele md) {
        gt = new GestionnaireTache();
        this.sc = sc;
        this.md = md;
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

}
