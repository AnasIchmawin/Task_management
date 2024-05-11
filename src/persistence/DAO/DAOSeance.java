package persistence.DAO;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import persistence.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class DAOSeance {

    // Create
    public void create(String titre, String dateDebut, String heureDebut, String dateFin, String heureFin,
            String description, String note, List<String> documents) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("seances");
            Document doc = new Document()
                    .append("titre", titre)
                    .append("dateDebut", dateDebut)
                    .append("heureDebut", heureDebut)
                    .append("dateFin", dateFin)
                    .append("heureFin", heureFin)
                    .append("description", description)
                    .append("note", note)
                    .append("documents", documents);
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la séance : " + e.getMessage());
        }
    }

    // Read
    public Document read(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("seances");
            ObjectId objId = new ObjectId(id);
            return collection.find(Filters.eq("_id", objId)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la séance : " + e.getMessage());
            return null;
        }
    }

    // Update

    public void update(String id, String titre, String dateDebut, String heureDebut, String dateFin, String heureFin,
            String description, String note, List<String> documents) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("seances");
            Document updateDoc = new Document("$set", new Document()
                    .append("titre", titre)
                    .append("dateDebut", dateDebut)
                    .append("heureDebut", heureDebut)
                    .append("dateFin", dateFin)
                    .append("heureFin", heureFin)
                    .append("description", description)
                    .append("note", note)
                    .append("documents", documents));
            ObjectId objId = new ObjectId(id);
            collection.updateOne(Filters.eq("_id", objId), updateDoc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de la séance : " + e.getMessage());
        }
    }

    // Delete

    public void delete(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("seances");
            ObjectId objId = new ObjectId(id);
            collection.deleteOne(Filters.eq("_id", objId));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la séance : " + e.getMessage());
        }
    }

    // Get all seances

    public List<Document> getAll() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("seances");
            FindIterable<Document> iterDoc = collection.find();
            List<Document> documents = new ArrayList<>();
            for (Document doc : iterDoc) {
                documents.add(doc);
            }
            return documents;
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des séances : " + e.getMessage());
            return null;
        }
    }
}
