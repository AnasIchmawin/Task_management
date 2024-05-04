package persistence.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import persistence.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOdocument {
    private static Integer Id = 0;

    private static int getId() {
        return Id ;
    }

    // Create
    public void create(String titre, String description, String path, LocalDateTime dateAjout) {
        try {
            // Récupérer la collection "documents"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("documents");

            // Ajouter les attributs du document
            Document doc = new Document();
            doc.append("id", getId() + 1)
                    .append("titre", titre)
                    .append("description", description)
                    .append("path", path)
                    .append("dateAjout", dateAjout);

            // Insérer le document dans la collection
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du document : " + e.getMessage());
        }
    }

    // Read
    public static Document read(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("documents");
            return collection.find(Filters.eq("id", id)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du document : " + e.getMessage());
            return null;
        }
    }

    // Update
    public void update(Integer id, String titre, String newDescription, String path, LocalDateTime dateAjout) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("documents");

            List<Document> updates = new ArrayList<>();
            if (titre != null) {
                updates.add(new Document("$set", new Document("titre", titre)));
            }
            if (newDescription != null) {
                updates.add(new Document("$set", new Document("description", newDescription)));
            }
            if (path != null) {
                updates.add(new Document("$set", new Document("path", path)));
            }
            if (dateAjout != null) {
                updates.add(new Document("$set", new Document("dateAjout", dateAjout)));
            }

            // Appliquer les mises à jour à la collection
            collection.updateOne(Filters.eq("id", id), Updates.combine(updates));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du document : " + e.getMessage());
        }
    }

    // Delete
    public void delete(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("documents");
            collection.deleteOne(Filters.eq("id", id));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du document : " + e.getMessage());
        }
    }
}
