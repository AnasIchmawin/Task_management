package persistence.DAO;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import metier.POJOProjet;
import persistence.DBConnection;

public class DAOProjet {
    
    // Create
    public void create(String titre, String description,String categorie, String type, String dateDebut, String dateFin, List<String> taches) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("projets");
            Document doc = new Document();
            doc.append("titre", titre)
                .append("description", description)
                .append("categorie", categorie)
                .append("type", type)
                .append("dateDebut", dateDebut)
                .append("dateFin", dateFin);
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
    public void update(String id, String titre, String description,String categorie, String type, String dateDebut, String dateFin, List<String> taches) {
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
            collection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }

    // Delete
    public void delete(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                            .getCollection("projets");
            ObjectId objId = new ObjectId(id);
            collection.deleteOne(Filters.eq("_id", objId));
        } catch (Exception e) {
                System.err.println("Erreur lors de la suppression de la projet : " + e.getMessage());
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
