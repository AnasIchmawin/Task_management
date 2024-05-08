package persistence.DAO;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import metier.POJOProjet;
import persistence.DBConnection;

public class DAOProjet {
    
    // Create
    public void create(String titre, String description, List<String> taches) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            Document doc = new Document();
            doc.append("titre", titre)
                .append("description", description);
            if(taches != null){
                List<Document> tachesList = new ArrayList<>();
                for (String id_tache : taches) {
                    Document tacheDoc = new Document();
                    tacheDoc.append("id", id_tache);
                    tachesList.add(tacheDoc);
                }
                doc.append("taches", tachesList);
            }
            else{
                doc.append("taches", null);
            }
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du projet : " + e.getMessage());
        }
    }

    // Read
    public Document read(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            return collection.find(Filters.eq("_id", id)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du projet : " + e.getMessage());
            return null;
        }
    }

    // Update
    public void update(String id, String titre, String description, List<Document> taches) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            Document doc = new Document();
            if (titre != null) {
                doc.append("titre", titre);
            }
            if (description != null) {
                doc.append("description", description);
            }
            if (taches != null) {
                doc.append("taches", taches);
            }
            collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }

    // Delete
    public void delete(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            collection.deleteOne(Filters.eq("id", id));
        } catch (MongoException e) {
            System.err.println("Erreur lors de la suppression du projet : " + e.getMessage());
        }
    }

    public List<Document> getAllProjects() {
        List<Document> allProjets = new ArrayList<>();
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            FindIterable<Document> cursor = collection.find();

            for (Document document : cursor) {
                allProjets.add(document);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la liste des projets : " + e.getMessage());
        }
        return allProjets;
    }
}
