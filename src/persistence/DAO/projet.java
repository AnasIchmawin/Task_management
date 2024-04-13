package persistence.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import persistence.DBConnection;

public class projet {
    private static int count = 0; // will be changed (store it in database)
    private Integer id;
    private String titre;
    private String categorie;
    private String type;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<seance> seances;
    private List<document> documents;
    private List<tache> taches;
    private boolean cloture;

    public projet(String titre, String categorie, String type, String description, LocalDateTime dateDebut,
            LocalDateTime dateFin, List<seance> seances, List<document> documents, List<tache> taches) {
        setCount(count + 1);
        id = getCount();
        this.titre = titre;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = seances != null ? seances : new ArrayList<>();
        this.documents = documents != null ? documents : new ArrayList<>();
        this.taches = taches != null ? taches : new ArrayList<>();
        this.cloture = false;
    }

    // Les getters et les setters
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
        projet.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<seance> getSeances() {
        return seances;
    }

    public void setSeances(List<seance> seances) {
        this.seances = seances;
    }

    public List<document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<document> documents) {
        this.documents = documents;
    }

    public List<tache> getTaches() {
        return taches;
    }

    public void setTaches(List<tache> taches) {
        this.taches = taches;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    // Les opérations CRUD


    // Create
    public void create() {
        // Récupérer la collection "taches"
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("projets");
        Document doc = new Document();
        // Ajouter les attributs du document
        doc.append("id", this.id)
                .append("titre", this.titre)
                .append("categorie", this.categorie)
                .append("type", this.type)
                .append("description", this.description)
                .append("dateDebut", this.dateDebut)
                .append("dateFin", this.dateFin);
        List<Document> seances = new ArrayList<>();
        for (seance s : this.seances) {
            seances.add(new Document("id", s.getId()));
        }
        doc.append("seances", seances);
        List<Document> documents = new ArrayList<>();
        for (document d : this.documents) {
            documents.add(new Document("id", d.getId()));
        }
        doc.append("documents", documents);
        List<Document> taches = new ArrayList<>();
        for (tache t : this.taches) {
            taches.add(new Document("id", t.getId()));
        }
        doc.append("taches", taches);
        doc.append("cloture", this.cloture);

        // Insérer le document dans la collection
        collection.insertOne(doc);

    }


    // Read 
    public static Document read(int id) {
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("projets");
        Document doc = collection.find(Filters.eq("id", id)).first();
        return doc;
    }


    // Update 
    public void update() {
        // Récupérer la collection "taches"
        MongoCollection<Document> collection = DBConnection.getInstance().getCollection("taches");

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

        // Mise à jour des seances associés
        List<Document> seanceList = new ArrayList<>();
        for (seance seanceItem : this.seances) {
            seanceList.add(new Document("id", seanceItem.getId()));
        }
        updates.add(new Document("$set", new Document("projets", seanceList)));

        // Mise à jour des documents associées
        List<Document> docList = new ArrayList<>();
        for (document docItem : this.documents) {
            docList.add(new Document("id", docItem.getId()));
        }
        updates.add(new Document("$set", new Document("documents", docList)));

        // Mise à jour des taches associées
        List<Document> tacheList = new ArrayList<>();
        for (tache tacheItem : this.taches) {
            tacheList.add(new Document("id", tacheItem.getId()));
        }
        updates.add(new Document("$set", new Document("taches", tacheList)));

        // Appliquer les mises à jour à la collection
        collection.updateOne(Filters.eq("id", this.id), Updates.combine(updates));
    }

    
    // Delete a project from the database
    public void delete() {
        // Récupérer la collection "projets"
        MongoCollection<Document> collection = DBConnection.getInstance()
                .getCollection("projets");

        // Créer le filtre pour sélectionner le projet à supprimer (par son ID)
        Document filter = new Document("id", this.id);

        // Supprimer le projet de la collection
        collection.deleteOne(filter);
    }

}
