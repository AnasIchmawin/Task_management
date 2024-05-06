package metier;

import java.util.List;
import org.bson.Document;

import metier.Errors.NonValidList;
import persistence.DAO.DAOProjet;

public class GestionnaireProjet {
    private DAOProjet daoProjet;
    private POJOProjet projet; 
    static Integer id = 1 ;

    private static int getId(){
        return id ;
    }

    private static void setId(Integer Id){
        id = Id ;
    }

    public GestionnaireProjet() {
        this.daoProjet = new DAOProjet();
        this.projet = new POJOProjet() ;
    }


    public DAOProjet getDaoProjet() {
        return daoProjet;
    }

    public void setDaoProjet(DAOProjet daoProjet) {
        this.daoProjet = daoProjet;
    }

    public POJOProjet getProjet() {
        return projet;
    }

    public void setProjet(POJOProjet projet) {
        this.projet = projet;
    }

    public void creerProjet() throws NonValidList {
        if (projet.getTitre().isEmpty()) {
            throw new NonValidList("Le projet doit avoir un nom !");
        }
        daoProjet.create(getId(), this.projet.getTitre(), this.projet.getCategorie(), this.projet.getType(), this.projet.getDescription(), this.projet.getDateDebut(), this.projet.getDateFin(), this.projet.getSeances(), this.projet.getDocuments(), this.projet.getTaches(), this.projet.getCloture());
        setId(id + 1);
    }

    // Method to retrieve a projet by its ID
    public Document lireProjet(int id) {
        return this.daoProjet.read(id);
    }

    // mettreAJourProjet
    public void mettreAJourProjet(int id, String nom, String description, String dateDebut, String dateFin, String chefProjet, String client, String etat) {
        this.daoProjet.update(id, nom, description, dateDebut, dateFin, chefProjet, client, etat);
    }

    // supprimerProjet
    public void supprimerProjet(int id) {
        this.daoProjet.delete(id);
    }

    // obtenirToutesLesProjets
    public List<Document> obtenirToutesLesProjets() {
        return this.daoProjet.getAllProjects();
    }

}
