package mygroup.presentation.seance;


import java.util.LinkedHashMap;

public class SeanceModel {
    private String Id;
    private String titre;
    private String description;
    private String dateDebut;
    private String dateFin;
    private String note;
    private LinkedHashMap<String, String> documents;
    private String pathDocClicked;


    public SeanceModel() {
        this.documents = new LinkedHashMap<>();
    }

    public SeanceModel(String titre, String description, String dateDebut, String dateFin, String note,
            LinkedHashMap<String, String> documents) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.note = note;
        this.documents = documents;
    }

    // Getters

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getNote() {
        return note;
    }

    public LinkedHashMap<String, String> getDocuments() {
        return documents;
    }

    // Setters

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDocuments(LinkedHashMap<String, String> documents) {
        this.documents = documents;
    }

    public void addDocToSeance(String idLastDoc, String titre) {
        documents.put(idLastDoc, titre);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    public void setDocPathCLicked(String docPath) {
        this.pathDocClicked = docPath;
    }

    public String getDocPathClicked() {
        return pathDocClicked;
    }
}
