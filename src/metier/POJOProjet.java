package metier;

import java.util.List;

import org.bson.Document;

public class POJOProjet {
    private Integer id;
    private String titre;
    private String categorie;
    private String type;
    private String description;
    private String dateDebut;
    private String dateFin;
    private List<Document> seances;
    private List<Document> documents;
    private List<Document> taches;
    private boolean cloture;

    // Getters and Setters
    public Integer getId() {
        return id;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public List<Document> getSeances() {
        return seances;
    }

    public void setSeances(List<Document> seances) {
        this.seances = seances;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getTaches() {
        return taches;
    }

    public void setTaches(List<Document> taches) {
        this.taches = taches;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    @Override
    public String toString() {
        return "POJOProjet [id=" + id + ", titre=" + titre + ", categorie=" + categorie + ", type=" + type
                + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", seances="
                + seances + ", documents=" + documents + ", taches=" + taches + ", cloture=" + cloture + "]";
    }
}
