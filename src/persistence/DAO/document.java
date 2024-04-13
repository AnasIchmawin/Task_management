package persistence.DAO;

import persistence.DBConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class document {
    private static int count = 0; // will be changed (store it in database)
    private Integer id;
    private String titre;
    private String description;
    private String path;
    private LocalDateTime dateAjout;

    // constructeur
    public document(String titre, String description, String path, LocalDateTime dateAjout) {
        setCount(count + 1);
        this.id = getCount();
        this.titre = titre;
        this.description = description;
        this.path = path;
        this.dateAjout = dateAjout;
    }

    // Les getters et les setters
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        document.count = count;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    // Les opertions CRUD

    // Create 
    public void create() {
        // Récupérer la collection "documents"
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("documents");
        Document doc = new Document();
        // Ajouter les attributs du document
        doc.append("id", this.id)
                .append("titre", this.titre)
                .append("description", this.description)
                .append("path", this.path)
                .append("dateAjout", this.dateAjout);
        // Insérer le document dans la collection
        collection.insertOne(doc);
    }


    //Read
    public static Document read(String TitreDoc) {
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("documents");
        Document doc = collection.find(Filters.eq("titre", TitreDoc)).first();
        return doc;
    }

    
    // Update
    public void update(Integer id, String titre, String newDescription,
            String path, LocalDateTime dateAjout) {

        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("documents");

        List<Bson> updates = new ArrayList<>();

        if (titre != null) {
            updates.add(Updates.set("titre", titre));
        }
        if (newDescription != null) {
            updates.add(Updates.set("description", newDescription));
        }
        if (path != null) {
            updates.add(Updates.set("path", path));
        }
        if (dateAjout != null) {
            updates.add(Updates.set("dateAjout", dateAjout));
        }
        // Combine toutes les operations de mise à jour
        Bson combinedUpdates = Updates.combine(updates);

        // appliquer les mises à jour
        collection.updateOne(Filters.eq("id", id), combinedUpdates);
    }

    
    //Delete
    public void delete(Integer id) {
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("documents");
        collection.deleteOne(Filters.eq("id", id));
    }
}