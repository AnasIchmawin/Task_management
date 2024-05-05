package metier;

import java.util.List;
import org.bson.Document;
import persistence.DAO.DAOProjet;

public class GestionnaireProjet {
    private DAOProjet daoProjet;

    public GestionnaireProjet() {
        this.daoProjet = new DAOProjet();
    }

    // Method to create a new project
    public void creerProjet(String titre, String categorie, String type, String description, String dateDebut,
            String dateFin, List<Document> seances, List<Document> documents, List<Document> taches, boolean cloture) {
        daoProjet.create(titre, categorie, type, description, dateDebut, dateFin, seances, documents, taches, cloture);
    }

    // Method to retrieve a project by its ID
    public Document lireProjet(int id) {
        return daoProjet.read(id);
    }

    // Method to update a project
    public void mettreAJourProjet(int id, String titre, String categorie, String type, String description,
            String dateDebut, String dateFin, List<Document> seances, List<Document> documents, List<Document> taches,
            boolean cloture) {
        daoProjet.update(id, titre, categorie, type, description, dateDebut, dateFin, seances, documents, taches,
                cloture);
    }

    // Method to delete a project by its ID
    public void supprimerProjet(int id) {
        daoProjet.delete(id);
    }

    // Methode pour recuperer les projets
    public List<Document> obtenirTousLesProjets() {
        return daoProjet.getAllProjects();
    }
}
