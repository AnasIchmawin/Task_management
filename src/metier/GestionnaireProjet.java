package metier;

import java.util.List;
import org.bson.Document;

import metier.Errors.NonValidList;
import persistence.DAO.DAOProjet;

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
        daoProjet.create(this.projet.getTitre(), this.projet.getDescription(), this.projet.getTaches());
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

}
