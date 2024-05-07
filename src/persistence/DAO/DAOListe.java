package persistence.DAO;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import persistence.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class DAOListe {

        // Create
        public void create(String Titre, String description, List<String> list) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");

                        // Ajouter les attributs du document
                        Document doc = new Document();
                        doc.append("titre", Titre)
                                        .append("description", description);
                        // covenvertit list to document
                        if(list != null){
                            List<Document> taches = new ArrayList<>();
                            for (String id_tache : list) {
                                    Document tacheDoc = new Document();
                                    tacheDoc.append("id", id_tache);
                                    taches.add(tacheDoc);
                            }
                            doc.append("taches", taches);
                        }
                        else{
                            doc.append("taches", null);
                        }
                        collection.insertOne(doc);
                } catch (Exception e) {
                        System.err.println("Erreur lors de la création de la liste : " + e.getMessage());
                }
        }

        // Read
        public Document read(String id) {
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
        public void update(String id, String Titre, String description, List<Document> taches) {
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
        public void delete(String id) {
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        collection.deleteOne(Filters.eq("id", id));
                } catch (Exception e) {
                        System.err.println("Erreur lors de la suppression de la liste : " + e.getMessage());
                }
        }

        public List<Document> getAllLists() {
                List<Document> allLists = new ArrayList<>();
                try {
                        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                                        .getCollection("listes");
                        FindIterable<Document> cursor = collection.find();

                        for (Document document : cursor) {
                                allLists.add(document);
                        }
                } catch (Exception e) {
                        System.err.println("Erreur lors de la récupération de toutes les listes : " + e.getMessage());
                }
                return allLists;
        }
}