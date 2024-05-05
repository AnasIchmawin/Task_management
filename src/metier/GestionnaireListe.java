package metier;

import java.util.List;
import org.bson.Document;
import persistence.DAO.DAOListe;

public class GestionnaireListe {

    private DAOListe daoListe;

    public GestionnaireListe() {
        this.daoListe = new DAOListe();
    }

    // Method to create a new list
    public void creerListe(String titre, String description, List<Document> taches) {
        daoListe.create(titre, description, taches);
    }

    // Method to retrieve a list by its ID
    public Document lireListe(int id) {
        return daoListe.read(id);
    }

    // Method to update a list
    public void mettreAJourListe(int id, String titre, String description, List<Document> taches) {
        daoListe.update(id, titre, description, taches);
    }

    // Method to delete a list by its ID
    public void supprimerListe(int id) {
        daoListe.delete(id);
    }

    // Method to get all lists
    public List<Document> obtenirToutesLesListes() {
        return daoListe.getAllLists();
    }
}
