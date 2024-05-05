package persistence.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import persistence.DBConnection;

public class DAOProjet {

    private static Integer Id = 0;

    private static int getId() {
        return Id;
    }
    public static void setId(Integer id) {
        Id = id;
    }
    // Create
    public void create(String titre, String categorie, String type, String description,
            String dateDebut, String dateFin, List<Document> seances,
            List<Document> documents, List<Document> taches, boolean cloture) {

        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");

            // Ajouter les attributs du document
            setId( Id + 1);
            Document doc = new Document();
            doc.append("id", getId() + 1)
                    .append("titre", titre)
                    .append("categorie", categorie)
                    .append("type", type)
                    .append("description", description)
                    .append("dateDebut", dateDebut)
                    .append("dateFin", dateFin)
                    .append("seances", seances)
                    .append("documents", documents)
                    .append("taches", taches)
                    .append("cloture", cloture);

            collection.insertOne(doc);
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la création du document projet: " + e.getMessage());
        }
    }

    // Read
    public Document read(int id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");
            Document doc = collection.find(Filters.eq("id", id)).first();
            if (doc == null) {
                System.out.println("Aucun document trouvé avec l'id : " + id);
                return null;
            }
            return doc;
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la lecture du document projet: " + e.getMessage());
            return null;
        }
    }


    // Delete
    public void delete(Integer id) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");

            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            if (result.getDeletedCount() == 0) {
                System.out.println("Aucun document trouvé avec l'id : " + id);
            } else {
                System.out.println("Document supprimé avec succès.");
            }
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la suppression du document projet: " + e.getMessage());
        }
    }
    public void update(int id2, String titre, String categorie, String type, String description, String dateDebut,
            String dateFin, List<Document> seances, List<Document> documents, List<Document> taches, boolean cloture) {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");
            Document doc = new Document();
            if (titre != null) {
                doc.append("titre", titre);
            }
            if (categorie != null) {
                doc.append("categorie", categorie);
            }
            if (type != null) {
                doc.append("type", type);
            }
            if (description != null) {
                doc.append("description", description);
            }
            if (dateDebut != null) {
                doc.append("dateDebut", dateDebut);
            }
            if (dateFin != null) {
                doc.append("dateFin", dateFin);
            }
            if (seances != null) {
                doc.append("seances", seances);
            }
            if (documents != null) {
                doc.append("documents", documents);
            }
            if (taches != null) {
                doc.append("taches", taches);
            }
            if (cloture) {
                doc.append("cloture", cloture);
            }
            collection.updateOne(Filters.eq("id", id2), new Document("$set", doc));
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la mise à jour du document projet: " + e.getMessage());
        }
    }
    
    public List<Document> getAllProjects() {
        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");
            return collection.find().into(new ArrayList<Document>());
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la lecture de tous les documents projet: " + e.getMessage());
            return null;
        }
    }

}
