package persistence.DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import persistence.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DAOTache {

    private static Integer Id = 0;

    private static int getId() {
        return Id ;
    }
    public static void setId(Integer id) {
        Id = id;
    }


    // Create
    public void create (String titre, String categorie, String description,
                       LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
                       Document projet, Document liste) {

        try {
            // Récupérer la collection "taches"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");

            // Ajouter les attributs du document
           setId( Id + 1);
            Document doc = new Document();
            doc.append("id", getId())
                    .append("titre", titre)
                    .append("categorie", categorie)
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
    public Document read(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("taches");
            // Rechercher et retourner le document correspondant à l'ID spécifié
            return collection.find(Filters.eq("id", id)).first();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la tâche : " + e.getMessage());
            return null;
        }
    }


    // Update
    public void update(Integer id, String titre, String categorie, String description,
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

    // GetAllTache : 
    public FindIterable<Document> getAllTache() {
        try {
            // Récupérer la collection "taches"
            return DBConnection.getInstance().getDatabase().getCollection("taches").find();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de toutes les tâches : " + e.getMessage());
            return null;
        }
    }
}
