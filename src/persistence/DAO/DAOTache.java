package persistence.DAO;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import persistence.DBConnection;
import java.util.ArrayList;
import java.util.List;


//mod
public class DAOTache {

    // Create tache
    public void create(String titre, Boolean etat, String categorie, String description, String dateDebut, String TempsDebut,
                   String dateFin, String TempsFin, List<String> documents, String projet, String liste) {
    try {
        MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("taches");
        Document doc = new Document()
                .append("titre", titre)
                .append("etat", etat)
                .append("categorie", categorie)
                .append("description", description)
                .append("dateDebut", dateDebut)
                .append("TempsDebut", TempsDebut)
                .append("dateFin", dateFin)
                .append("TempsFin", TempsFin)
                .append("documents", documents)
                .append("projet", projet)
                .append("liste", liste);       
        collection.insertOne(doc);
    } catch (Exception e) {
        System.err.println("Error creating task: " + e.getMessage());
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
    public void update(String titre,Boolean etat , String categorie, String description, 
    String dateDebut, String tempsDebut, String dateFin,
    String tempsFin, List<String> list, String projet, String liste) {
        try {
            // Récupérer la collection "taches"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase().getCollection("taches");

            // Créer les mises à jour à appliquer
            List<Document> updates = new ArrayList<>();

            // Ajouter les attributs non nuls à mettre à jour
            if (titre != null) {
                updates.add(new Document("$set", new Document("titre", titre)));
            }
            if (etat != null) {
                updates.add(new Document("$set", new Document("etat", etat)));
            }
            if (categorie != null) {
                updates.add(new Document("$set", new Document("categorie", categorie)));
            }
            if (description != null) {
                updates.add(new Document("$set", new Document("description", description)));
            }
            if (dateDebut != null) {
                updates.add(new Document("$set", new Document("dateDebut", dateDebut)));
            }
            if (tempsDebut != null) {
                updates.add(new Document("$set", new Document("HeureDebut", tempsDebut)));
            }
            if (dateFin != null) {
                updates.add(new Document("$set", new Document("dateFin", dateFin)));
            }
            if (tempsFin != null) {
                updates.add(new Document("$set", new Document("HeureDebut", tempsFin)));
            }
            if (list != null) {
                updates.add(new Document("$set", new Document("documents", list)));
            }
            if (projet != null) {
                updates.add(new Document("$set", new Document("projet", projet)));
            }
            if (liste != null) {
                updates.add(new Document("$set", new Document("liste", liste)));
            }

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

    //GetEtat
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