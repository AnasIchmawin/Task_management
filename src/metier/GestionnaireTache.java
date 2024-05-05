package metier;

import org.bson.Document;
import persistence.DAO.DAOTache;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireTache {
    private DAOTache daoTache;
    private POJOTache pojoTache ;
    static Integer Id ;
    

    public static Integer getId() {
        return Id;
    }

    public static void setId(Integer id) {
        Id = id;
    }

    public GestionnaireTache() {
        this.daoTache = new DAOTache();
    }

    // Create
    public void createTask(String titre, String categorie, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {
                setId(Id + 1);
        daoTache.create(getId(), titre, categorie, description, dateDebut, dateFin, documents, projet, liste);
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
        return daoTache.getAllTache().into(new ArrayList<>());
    }
}
