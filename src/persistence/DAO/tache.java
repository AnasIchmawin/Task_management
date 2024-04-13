package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import persistence.DBConnection;
import java.util.ArrayList;

public class tache {
    private static int count = 0; // sera modifié (stocké dans la base de données)
    private Integer id;
    private String titre;
    private String categorie;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<document> documents;
    private List<projet> projets;
    private liste liste;

    // constructeur
    public tache(String titre, String categorie, String description, LocalDateTime dateDebut,
            LocalDateTime dateFin, List<document> documents, List<projet> projets, liste liste) {
        setCount(count + 1);
        this.id = getCount();
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.documents = documents != null ? documents : new ArrayList<>();
        this.projets = projets != null ? projets : new ArrayList<>();
        this.liste = liste;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        tache.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<document> documents) {
        this.documents = documents;
    }

    public List<projet> getprojet() {
        return this.projets;
    }

    public void setProjet(List<projet> projets) {
        this.projets = projets;
    }

    public liste getListe() {
        return liste;
    }

    public void setListe(liste liste) {
        this.liste = liste;
    }

    public String getCategorie() {
        return categorie;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    // Les operations CRUD

    //Create 
    public void create() {
        // Récupérer la collection "taches"
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("taches");
        Document doc = new Document();
        // Ajouter les attributs du document
        doc.append("id", this.id)
                .append("titre", this.titre)
                .append("categorie", this.categorie)
                .append("description", this.description)
                .append("dateDebut", this.dateDebut)
                .append("dateFin", this.dateFin);

        // ajouter les documents
        List<Document> documentList = new ArrayList<>();
        for (document documentItem : this.documents) {
            documentList.add(new Document("id", documentItem.getId()));
        }
        doc.append("documents", documentList);
        // ajouter les projets
        for (projet projetItem : this.projets) {
            documentList.add(new Document("id", projetItem.getId()));
        }
        doc.append("projets", documentList);
        // ajouter la liste
        if (this.liste != null) {
            doc.append("liste", new Document("id", this.liste.getId()));
        }
        collection.insertOne(doc);
    }


    // Read 
    public static Document read(Integer id) {
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("taches");
        Document doc = collection.find(Filters.eq("id", id)).first();
        return doc;
    }


    // Update
    public void update() {
        // Récupérer la collection "taches"
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("taches");

        // Créer les mises à jour à appliquer
        List<Document> updates = new ArrayList<>();
        updates.add(new Document("$set", new Document("titre", this.titre)));
        updates.add(new Document("$set", new Document("categorie", this.categorie)));
        updates.add(new Document("$set", new Document("description", this.description)));
        updates.add(new Document("$set", new Document("dateDebut", this.dateDebut)));
        updates.add(new Document("$set", new Document("dateFin", this.dateFin)));

        // Mise à jour des documents associés
        List<Document> documentList = new ArrayList<>();
        for (document documentItem : this.documents) {
            documentList.add(new Document("id", documentItem.getId()));
        }
        updates.add(new Document("$set", new Document("documents", documentList)));

        // Mise à jour des projets associés
        List<Document> projetList = new ArrayList<>();
        for (projet projetItem : this.projets) {
            projetList.add(new Document("id", projetItem.getId()));
        }
        updates.add(new Document("$set", new Document("projets", projetList)));

        // Mise à jour de la liste associée
        if (this.liste != null) {
            updates.add(new Document("$set",
                    new Document("liste", new Document("id", this.liste.getId()))));
        } else {
            updates.add(new Document("$unset", new Document("liste", "")));
        }

        // Appliquer les mises à jour à la collection
        collection.updateOne(Filters.eq("id", this.id), Updates.combine(updates));
    }


    // Delete 
    public void delete() {
        // Récupérer la collection "taches"
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("taches");
        // Supprimer la tâche de la collection
        collection.deleteOne(Filters.eq("id", this.id));
    }
}