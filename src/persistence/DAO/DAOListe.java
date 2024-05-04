package persistence.DAO;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import persistence.DBConnection;
import java.util.List;

public class DAOListe {
        private static Integer Id = 0;

        private static int getId() {
                return Id;
        }

        public static void setId(Integer id) {
                Id = id;
        }

        // Create
        public void create(String Titre, String description, List<Document> taches) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");

                        // Ajouter les attributs du document
                        setId(Id + 1);
                        Document doc = new Document();
                        doc.append("id", getId())
                                        .append("titre", Titre)
                                        .append("description", description);
                        if (taches != null) {
                                doc.append("taches", taches);
                        }
                        // Insert the document
                        collection.insertOne(doc);
                } catch (Exception e) {
                        System.err.println("Erreur lors de la création de la liste : " + e.getMessage());
                }
        }

        // Read
        public static Document read(int id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        return collection.find(Filters.eq("id", id)).first();
                } catch (Exception e) {
                        System.err.println("Erreur lors de la lecture de la liste : " + e.getMessage());
                        return null;
                }
        }

        // Update
        public void update(Integer id, String Titre ,String description, List<Document> taches) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        Document doc = new Document();
                        if (Titre != null) {
                                doc.append("titre", Titre);
                        }
                        if (description != null) {
                                doc.append("description", description);
                        }
                        if (taches != null) {
                                doc.append("taches", taches);
                        }
                        collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
                } catch (Exception e) {
                        System.err.println("Erreur lors de la mise à jour de la liste : " + e.getMessage());
                }
        }

        // Delete
        public void delete(Integer id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        collection.deleteOne(Filters.eq("id", id));
                } catch (Exception e) {
                        System.err.println("Erreur lors de la suppression de la liste : " + e.getMessage());
                }
        }
}
