package persistence.DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import persistence.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOTache {

    // Create
    public void create(String titre, String categorie,Boolean etat, String description, 
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {

        try {
            // Récupérer la collection "taches"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            // Ajouter les attributs du document
            Document doc = new Document();
                    doc.append("titre", titre)
                    .append("categorie", categorie)
                    .append("etat", etat)
                    .append("description", description)
                    .append("dateDebut", dateDebut)
                    .append("dateFin", dateFin)
                    .append("documents", documents)
                    .append("projet", projet)
                    .append("liste", liste);

            // Insérer le document dans la collection
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la tâche : " + e.getMessage());
        }
    }

    // Read
    public Document read(String id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            // Rechercher et retourner le document correspondant à l'ID spécifié
            ObjectId Oid = new ObjectId(id);
            return collection.find(Filters.eq("_id", Oid)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la tâche : " + e.getMessage());
            return null;
        }
    }

    // Update
    public void update(Integer id, String titre, String categorie, Boolean etat, String description, 
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            Document projet, Document liste) {
        try {
            // Récupérer la collection "taches"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("taches");

            // Créer les mises à jour à appliquer
            List<Document> updates = new ArrayList<>();

            // Ajouter les attributs non nuls à mettre à jour
            if (titre != null) {
                updates.add(new Document("$set", new Document("titre", titre)));
            }
            if (categorie != null) {
                updates.add(new Document("$set", new Document("categorie", categorie)));
            }
            if (etat != null) {
                updates.add(new Document("$set", new Document("etat", etat)));
            }
            if (description != null) {
                updates.add(new Document("$set", new Document("description", description)));
            }
            if (dateDebut != null) {
                updates.add(new Document("$set", new Document("dateDebut", dateDebut)));
            }
            if (dateFin != null) {
                updates.add(new Document("$set", new Document("dateFin", dateFin)));
            }
            if (documents != null) {
                updates.add(new Document("$set", new Document("documents", documents)));
            }
            if (projet != null) {
                updates.add(new Document("$set", new Document("projet", projet)));
            }
            if (liste != null) {
                updates.add(new Document("$set", new Document("liste", liste)));
            }

            // Appliquer les mises à jour à la collection
            collection.updateOne(Filters.eq("id", id), Updates.combine(updates));
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de la tâche : " + e.getMessage());
        }
    }

    // Delete
    public void delete(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            collection.deleteOne(Filters.eq("id", id));
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
        }
    }

    // GetAllTache return Map value and titre : 
    public List<Document> getAllTaches() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            FindIterable<Document> iterDoc = collection.find();
            List<Document> taches = new ArrayList<>();
            for (Document doc : iterDoc) {
                taches.add(doc);
            }
            return taches;
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des tâches au niveau de Dao: " + e.getMessage());
            return null;
        }
    }

    // GetTitre
    public String getTitre(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            
            // Convert tacheId to ObjectId
            ObjectId objectId = new ObjectId(tacheId);
            
            Document tache = collection.find(Filters.eq("_id", objectId)).first();
            if (tache != null) {
                return tache.getString("titre");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving the title of the task: " + e.getMessage());
            return null;
        }
    }

    // GetEtat
    public Boolean getEtat(String tacheId) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            
            // Convert tacheId to ObjectId
            ObjectId objectId = new ObjectId(tacheId);
            
            Document tache = collection.find(Filters.eq("_id", objectId)).first();
            if (tache != null) {
                return tache.getBoolean("etat");
            } else {
                System.err.println("Task not found with ID: " + tacheId);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving the state of the task: " + e.getMessage());
            return null;
        }
    }

    // SetEtat
    public void setEtat(String tacheId, Boolean etat) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            
            // Convert tacheId to ObjectId
            ObjectId objectId = new ObjectId(tacheId);
            
            collection.updateOne(Filters.eq("_id", objectId), Updates.set("etat", etat));
        } catch (Exception e) {
            System.err.println("Error setting the state of the task: " + e.getMessage());
        }
    }
}
