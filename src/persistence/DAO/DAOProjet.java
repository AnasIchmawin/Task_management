package persistence.DAO;

import java.time.LocalDateTime;
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

    // Create
    public void create(String titre, String categorie, String type, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> seances,
            List<Document> documents, List<Document> taches, boolean cloture) {

        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");

            // Ajouter les attributs du document
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
    public static Document read(int id) {
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

    public void update(Integer id, String titre, String categorie, String description,
            LocalDateTime dateDebut, LocalDateTime dateFin, List<Document> documents,
            List<Document> seances, List<Document> taches, boolean cloture) {

        // Validation des entrées
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id invalide.");
        }

        try {
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("projets");

            Document doc = new Document();
            doc.append("titre", titre);
            doc.append("categorie", categorie);
            doc.append("description", description);
            doc.append("dateDebut", dateDebut);
            doc.append("dateFin", dateFin);
            doc.append("documents", documents);
            doc.append("seances", seances);
            doc.append("taches", taches);
            doc.append("cloture", cloture);
            collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
        } catch (MongoException e) {
            // Gérer l'exception ici (par exemple, enregistrer l'erreur et/ou quitter le
            // programme)
            System.err.println("Erreur lors de la mise à jour du document projet: " + e.getMessage());
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

}
