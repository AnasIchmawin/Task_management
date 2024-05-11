package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import persistence.DBConnection;

public class DAODocument {
    public void create(String titre, String description,String URL,
            LocalDateTime dateAjout) {

        try {
            // Récupérer la collection "Documents"
            MongoCollection<Document> collection = DBConnection.getInstance().getDatabase()
                    .getCollection("Documrnts");

            // Ajouter les attributs du document
            Document doc = new Document();
                    doc.append("titre", titre)
                    .append("description", description)
                    .append("dateInsertion", dateAjout)
                    .append("URL", URL);
                    //.append("projet", projet)
                    //.append("liste", liste)
                    


            // Insérer le document dans la collection
            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du document : " + e.getMessage());
        }
    }
}
