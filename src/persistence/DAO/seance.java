package persistence.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import persistence.DBConnection;

public class seance {
    private static int count = 0; // will be changed (store it in database)
    private Integer id;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String note;
    private List<document> documents;
    private projet projet;

// constructeur
    public seance(String description, LocalDateTime dateDebut, LocalDateTime dateFin, String note,
            List<document> documents, projet projet) {
        setCount(count + 1);
        id = getCount();
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.note = note;
        this.documents = documents;
        this.projet = projet;
    }

      // getters and setters
    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        seance.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<document> documents) {
        this.documents = documents;
    }

    public projet getProjet() {
        return projet;
    }

    public void setProjet(projet projet) {
        this.projet = projet;
    }

    // Les Operations CRUD

    // create seance
    public void create() {
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("seances");
        Document doc = new Document();
        doc.append("id", id)
                .append("description", description)
                .append("dateDebut", dateDebut)
                .append("dateFin", dateFin)
                .append("note", note);

        // Ajouter les documents
        List<Document> docs = new ArrayList<>();
        for (document document : documents) {
            docs.add(new Document("id", document.getId()));
        }
        doc.append("documents", docs);

        // Ajouter le projet
        List<Document> projets = new ArrayList<>();
        projets.add(new Document("id", projet.getId()));
        doc.append("projet", projets);

        // Insérer le document dans la collection
        collection.insertOne(doc);
    }


    // Read a seance
    public static Document read(int id) {
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("seances");
        Document doc = collection.find(Filters.eq("id", id)).first();
        return doc;
    }


    // Update a seance
    public void update() {
        MongoCollection<Document> collection = DBConnection.getInstance()
        .getCollection("seances");
        Document doc = new Document();
        doc.append("id", id)
                .append("description", description)
                .append("dateDebut", dateDebut)
                .append("dateFin", dateFin)
                .append("note", note);

        // Ajouter les documents
        List<Document> docs = new ArrayList<>();
        for (document document : documents) {
            docs.add(new Document("id", document.getId()));
        }
        doc.append("documents", docs);

        // Ajouter le projet
        List<Document> projets = new ArrayList<>();
        projets.add(new Document("id", projet.getId()));
        doc.append("projet", projets);

        // Mettre à jour le document dans la collection
        collection.updateOne(Filters.eq("id", id), new Document("$set", doc));
    }

    
    // Delete a seance
    public void delete() {
        MongoCollection<Document> collection = DBConnection.getInstance()
        .getCollection("seances");
        collection.deleteOne(Filters.eq("id", id));
    }
}
