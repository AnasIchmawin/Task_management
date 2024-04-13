package persistence.DAO;

import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import persistence.DBConnection;

public class liste {
    private static int count = 0; // will be changed (store it in database)
    private Integer id;
    private String description;
    private List<tache> taches;

    public liste(String description, List<tache> taches) {
        setCount(count + 1);
        id = getCount();
        this.description = description;
        this.taches = taches;
    }

    // get count
    public static int getCount() {
        return count;
    }

    // set count
    public static void setCount(int count) {
        liste.count = count;
    }

    // get id
    public Integer getId() {
        return id;
    }

    // set id
    public void setId(Integer id) {
        this.id = id;
    }

    // get description
    public String getDescription() {
        return description;
    }

    // set description
    public void setDescription(String description) {
        this.description = description;
    }

    // get taches
    public List<tache> getTaches() {
        return taches;
    }

    // set taches
    public void setTaches(List<tache> taches) {
        this.taches = taches;
    }

    // Les opertions CRUD

    
    // Create a liste
    public void create() {
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("liste");
        Document doc = new Document();
        doc.append("id", id)
                .append("description", description)
                .append("taches", taches);
        // insert the document
        collection.insertOne(doc);
    }



    // Read a liste
    public static Document read(int id) {
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("listes");
        Document doc = collection.find(Filters.eq("id", id)).first();
        return doc;
    }



    // Update a liste
    public void update() {
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("listes");
        Document doc = new Document();
        doc.append("id", id)
                .append("description", description)
                .append("taches", taches);
        collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
    }



    // Delete a liste
    public void delete() {
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("listes");
        collection.deleteOne(Filters.eq("id", id));
    }

}
