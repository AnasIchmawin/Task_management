package metier;

import org.bson.Document;
import org.bson.types.ObjectId;

import persistence.DAO.DAOTache;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireTache {
    private DAOTache daoTache;
    private POJOTache pojoTache;
    static Integer Id;

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

    // GetAllTask saus dorm map<Integer _id and String titre>
    // public Map<String, String> getAllTasks_Id_Title() {
    //     try {
    //         Map<String, String> tasks = new HashMap<>();
    //         List<Document> documents = daoTache.getAllTaches();
    //         for (Document document : documents) {
    //             ObjectId objectId = document.getObjectId("_id");
    //             String taskId = objectId.toString(); // Convertir ObjectId en String
    //             String taskTitle = document.getString("titre");
    //             tasks.put(taskId, taskTitle);
    //         }
    //         for (Map.Entry<String, String> entry : tasks.entrySet()) {
    //             System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    //         }
    //         return tasks;
    //     } catch (Exception e) {
    //         System.err.println("Erreur lors de la récupération des tâches au niveau gestion : " + e.getMessage());
    //         return null;
    //     }
    // }

    // GetAllTask
    public List<Document> getAllTasks() {
        return daoTache.getAllTaches();
    }

}
