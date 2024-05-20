package mygroup.metier.POJO;


import java.util.List;

import org.bson.Document;

public class POJOProjet {
    private String titre;
    private String description;
    private String Categorie;
    private String Type;
    private String DateDebut;
    private String DateFin;
    private List<Document> Taches;
    private List<Document> Documents;
    private List<Document> Seances;

    public POJOProjet() {
    }

    public POJOProjet(String titre, String description, String categorie, String type, String dateDebut, String dateFin,
            List<Document> taches, List<Document> documents, List<Document> seances) {
        this.titre = titre;
        this.description = description;
        Categorie = categorie;
        Type = type;
        DateDebut = dateDebut;
        DateFin = dateFin;
        Taches = taches;
        Documents = documents;
        Seances = seances;
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

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String dateDebut) {
        DateDebut = dateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }

    public List<Document> getTaches() {
        return Taches;
    }

    public void setTaches(List<Document> taches) {
        Taches = taches;
    }

    public List<Document> getDocuments() {
        return Documents;
    }

    public void setDocuments(List<Document> documents) {
        Documents = documents;
    }

    public List<Document> getSeances() {
        return Seances;
    }

    public void setSeances(List<Document> seances) {
        Seances = seances;
    }

    @Override
    public String toString() {
        return "POJOProjet [titre=" + titre + ", description=" + description + ", Categorie=" + Categorie + ", Type="
                + Type + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", Taches=" + Taches + ", Documents="
                + Documents + ", Seances=" + Seances + "]";
    }

  
}
