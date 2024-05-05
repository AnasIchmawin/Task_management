package metier;

import java.util.List;
import org.bson.Document;
import persistence.DAO.DAOListe;

public class GestionnaireListe {

    private static DAOListe daoListe;
    static Integer id = 1 ;
    
    private static void setId(Integer Id){
        id = Id ;
    }

    public GestionnaireListe() {
        GestionnaireListe.daoListe = new DAOListe();
    }

    // Method to create a new list
    public void creerListe(String titre, String description, List<Document> taches) {
        daoListe.create(id, titre, description, taches);
        setId(id + 1);
    }

    // Method to retrieve a list by its ID
    public Document lireListe(int id) {
        return DAOListe.read(id);
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
