package mygroup.metier.Gestionnaire ;
import mygroup.metier.POJO.POJOProjet;

import java.util.LinkedHashMap;
import java.util.List;
import org.bson.Document;

import mygroup.metier.Errors.*;
import mygroup.persistence.DAO.DAOProjet;

public class GestionnaireProjet {
    private DAOProjet daoProjet;
    private POJOProjet projet;

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
        daoProjet.create(this.projet.getTitre(), this.projet.getDescription(), this.projet.getCategorie(), this.projet.getType(),
                            this.projet.getDateDebut(), this.projet.getDateFin(), this.projet.getTaches(), this.projet.getSeances());
    }

    // Method to retrieve a projet by its ID
    public Document lireProjet(String id) {
        return this.daoProjet.read(id);
    }

    // mettreAJourProjet
    public void mettreAJourProjet(String id, String nom, String description, List<Document> taches) {
        this.daoProjet.update(id, nom, description, taches);
    }

    // supprimerProjet
    public void supprimerProjet(List<String> ids) {
        try {
            for (String id : ids)
                daoProjet.delete(id);
        } catch (Exception e) {
            System.out.println("Supression failed");
        }
    }

    // obtenirToutesLesProjets
    public List<Document> obtenirToutesLesProjets() {
        return this.daoProjet.getAllProjects();
    }

    //obtenirProjet
    public Document obtenirProjet(String id) {
        return this.daoProjet.read(id);
    }


    public LinkedHashMap<String, String> getProjetsArchiver() {
        return this.daoProjet.getArchivedProjects();
    }

}
