package metier;

import java.util.LinkedHashMap;
import java.util.List;
import org.bson.Document;

import metier.Errors.NonValidList;
import persistence.DAO.DAOListe;

public class GestionnaireListe {

    private DAOListe daoListe;
    private POJOListe liste;

    public GestionnaireListe() {
        this.daoListe = new DAOListe();
        this.liste = new POJOListe();
    }

    public DAOListe getDaoListe() {
        return daoListe;
    }

    public POJOListe getListe() {
        return liste;
    }

    //getTaches
    public LinkedHashMap<String,Boolean> getTaches(String listeId) {
        return daoListe.getTaches(listeId);
    }

    public void setDaoListe(DAOListe daoListe) {
        this.daoListe = daoListe;
    }

    public void setListe(POJOListe liste) {
        this.liste = liste;
        System.out.println("Liste dans setListe dans le gestionnaire : " + liste);
    }

    // Method to create a new list
    public void creerListe() throws NonValidList {
        if (liste.getTitre().isEmpty()) {
            throw new NonValidList("La liste doit avoir un titre !");
        }
        daoListe.create(this.liste.getTitre(), this.liste.getDescription(), this.liste.getTaches());
    }

    // Method to retrieve a list by its ID
    public Document lireListe(String id) {
        return daoListe.read(id);
    }

    //getListTitle
    public String getListTitle(String id) {
        return daoListe.getListTitle(id);
    }

    //getListDescription
    public String getListDescription(String id) {
        return daoListe.getListDescription(id);
    }

    // Method to update a list
    public void mettreAJourListe(String id, String titre, String description, List<Document> taches) {
        daoListe.update(id, titre, description, taches);
    }

    // Method to delete a list by its ID
    public void supprimerListe(List<String> ids) {
        try {
            for (String id : ids)
                daoListe.delete(id);
        } catch (Exception e) {
            System.out.println("Supression failed");
        }
    }

    // Method to get all lists
    public List<Document> obtenirToutesLesListes() {
        return daoListe.getAllLists();
    }
}
