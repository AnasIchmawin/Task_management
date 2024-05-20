package mygroup.presentation.tache_detail;


import java.util.ArrayList;
import java.util.LinkedHashMap;

public class tacheDetailModel {
    private String title;
    private String dateDebut;
    private String dateFin;
    private String categorie;
    private String type;
    private String description;
    private LinkedHashMap<String, ArrayList<String>> ListOfDocuments;

    public tacheDetailModel() {
        ListOfDocuments = new LinkedHashMap<>();
    }

    // Constructor
    public tacheDetailModel(String title, String dateDebut, String dateFin, String categorie, String type, String description) {
        this.title = title;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
        ListOfDocuments = new LinkedHashMap<>();
    }
    // Getters and Setters
    public String getTitleLable() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public void addDocToTache(String idLastDoc, String titre, String description2, String url) {
        ArrayList<String> doc = new ArrayList<>();
        doc.add(titre);
        doc.add(description2);
        doc.add(url);
        ListOfDocuments.put(idLastDoc, doc);
    }
    public LinkedHashMap<String, ArrayList<String>> getListOfDocuments() {
        return ListOfDocuments;
        }
}
