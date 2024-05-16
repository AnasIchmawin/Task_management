package mygroup.persistence.DAO ;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import mygroup.persistence.DBConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAODocument {

    // Create
    public void create(String titre, String description, String path) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
            Document doc = new Document("titre", titre)
                    .append("description", description)
                    .append("path", path)
                    .append("datedAjout", (LocalDate.now()).toString());
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du document : " + e.getMessage());
        }
    }


    public Document read(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
             ObjectId objId = new ObjectId(id);
            return collection.find(Filters.eq("_id", objId)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du document : " + e.getMessage());
            return null;
        }
    }

    // Update
    public void update(String id, String titre, String description, String path, LocalDateTime datedAjout) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
            Document updateDoc = new Document("$set", new Document("titre", titre)
                    .append("description", description)
                    .append("path", path)
                    .append("datedAjout", datedAjout));
                    ObjectId objId = new ObjectId(id);
            collection.updateOne(Filters.eq("_id", objId), updateDoc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du document : " + e.getMessage());
        }
    }

    // Delete
    public void delete(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
            ObjectId objId = new ObjectId(id);
            collection.deleteOne(Filters.eq("_id", objId));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du document : " + e.getMessage());
        }
    }

    // Get all documents
    public List<Document> getAllDocuments() {
        List<Document> allDocuments = new ArrayList<>();
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
            FindIterable<Document> cursor = collection.find();
            for (Document document : cursor) {
                allDocuments.add(document);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de tous les documents : " + e.getMessage());
        }
        return allDocuments;
    }

    //getIdLastDoc
    public String getIdLastDoc() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("documents");
            Document doc = collection.find().sort(new Document("_id", -1)).first();
            return doc.get("_id").toString();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de l'id du dernier document : " + e.getMessage());
            return null;
        }
    }
}
