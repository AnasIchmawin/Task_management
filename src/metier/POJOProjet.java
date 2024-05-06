package metier;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class POJOProjet {
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

    public POJOProjet() {
    }

    public POJOProjet(String titre, String categorie, String type, String description, String dateDebut,
            String dateFin, List<Document> seances, List<Document> documents, List<Document> taches, boolean cloture) {
        this.titre = titre;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = seances;
        this.documents = documents;
        this.taches = taches;
        this.cloture = cloture;
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

    public boolean getCloture() {
        return cloture;
    }

    @Override
    public String toString() {
        return "POJOProjet [titre=" + titre + ", categorie=" + categorie + ", type=" + type
                + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", seances="
                + seances + ", documents=" + documents + ", taches=" + taches + ", cloture=" + cloture + "]";
    }

}
