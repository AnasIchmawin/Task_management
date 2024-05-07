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
    public void createTask(String titre, String categorie, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {
        daoTache.create(titre, categorie, description, dateDebut, dateFin, documents, projet, liste);
    }

    // Read
    public Document readTask(Integer id) {
        return daoTache.read(id);
    }

    // Update
    public void updateTask(Integer id, String titre, String categorie, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {
        daoTache.update(id, titre, categorie, description, dateDebut, dateFin, documents, projet, liste);
    }

    // Delete
    public void deleteTask(Integer id) {
        daoTache.delete(id);
    }

    // GetAllTask
    public List<Document> getAllTasks() {
        return daoTache.getAllTaches();
    }

}
