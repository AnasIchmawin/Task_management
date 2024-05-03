package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import persistence.DBConnection;

public class DAOSeance {

    // Create a seance
    public void create(Integer id, String description, LocalDateTime dateDebut, LocalDateTime dateFin, double note,
            List<Document> documents, Document projet) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("seances");
            Document doc = new Document();
            doc.append("id", id)
                    .append("description", description)
                    .append("dateDebut", dateDebut)
                    .append("dateFin", dateFin)
                    .append("note", note)
                    .append("documents", documents)
                    .append("projet", projet);

            // Insérer le document dans la collection
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la séance : " + e.getMessage());
        }
    }

    // Read a seance
    public static Document read(int id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("seances");
            return collection.find(Filters.eq("id", id)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la séance : " + e.getMessage());
            return null;
        }
    }

    // Update a seance
    public void update(Integer id, String description, LocalDateTime dateDebut, LocalDateTime dateFin, double note,
            List<Document> documents, Document projet) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("seances");
            Document doc = new Document();
            doc.append("id", id)
                    .append("description", description)
                    .append("dateDebut", dateDebut)
                    .append("dateFin", dateFin)
                    .append("note", note)
                    .append("documents", documents)
                    .append("projet", projet);

            // Mettre à jour le document dans la collection
            collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de la séance : " + e.getMessage());
        }
    }

    // Delete a seance
    public void delete(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("seances");
            collection.deleteOne(Filters.eq("id", id));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la séance : " + e.getMessage());
        }
    }
}
