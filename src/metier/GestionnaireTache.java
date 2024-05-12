package metier;

import org.bson.Document;
import persistence.DAO.DAOTache;
import java.time.LocalDateTime;
import java.util.List;

public class GestionnaireTache {
    private DAOTache daoTache;
    private POJOTache pojoTache;

    public GestionnaireTache() {
        this.daoTache = new DAOTache();
    }

    // Create
 
    public void createTask(POJOTache nouveauTache) {
        try {
            daoTache.create(nouveauTache.getTitre(), nouveauTache.getCategorie(), nouveauTache.getEtat(),
                    nouveauTache.getDescription(), nouveauTache.getDateDebut(), nouveauTache.getDateFin(),
                    nouveauTache.getDocuments(), nouveauTache.getProjet(), nouveauTache.getListe());
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la tâche : " + e.getMessage());
        }
    }

    // Read
    public Document readTask(String id) {
        return daoTache.read(id);
    }

    // Update
    public void updateTask(Integer id, String titre, String categorie,Boolean etat, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {
        daoTache.update(id, titre, categorie, etat, description, dateDebut, dateFin, documents, projet, liste);
    }

    // Delete
    public void deleteTask(Integer id) {
        daoTache.delete(id);
    }


    // GetAllTask
    public List<Document> getAllTasks() {
        return daoTache.getAllTaches();
    }

    // getTitle
    public String getTitle(String tacheId) {
        return daoTache.getTitre(tacheId);
    }

    // getTaskEtat
    public Boolean getTaskEtat(String tacheId) {
        return daoTache.getEtat(tacheId);
    }

    // setTaskEtat
    public void setTaskEtat(String tacheId, Boolean etat) {
        daoTache.setEtat(tacheId, etat);
    }
}
