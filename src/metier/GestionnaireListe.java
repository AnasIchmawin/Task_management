package metier;

import java.util.List;
import org.bson.Document;

import metier.Errors.NonValidList;
import persistence.DAO.DAOListe;

public class GestionnaireListe {

    private DAOListe daoListe;
    private POJOListe liste; 
    static Integer id = 1 ;

    private static int getId(){
        return id ;
    }
    
    private static void setId(Integer Id){
        id = Id ;
    }

    public GestionnaireListe() {
        this.daoListe = new DAOListe();
        this.liste = new POJOListe() ;
    }

    


    public DAOListe getDaoListe() {
        return daoListe;
    }

    public POJOListe getListe() {
        return liste;
    }


    public void setDaoListe(DAOListe daoListe) {
        this.daoListe = daoListe;
    }

    public void setListe(POJOListe liste) {
        this.liste = liste;
    }

    // Method to create a new list
    public void creerListe() throws NonValidList {
        if (liste.getTitre().isEmpty()) {
            throw new NonValidList("La liste doit avoir un titre !");
        }
        daoListe.create(getId(), this.liste.getTitre(), this.liste.getDescription(), this.liste.getTaches());
        setId(id + 1);
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
