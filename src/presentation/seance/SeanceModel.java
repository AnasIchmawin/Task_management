package presentation.seance;

import java.util.List;

public class SeanceModel {
    String titre;
    String description;
    String dateDebut;
    String dateFin;
    String note;
    List<String> documents;

    public SeanceModel(String titre, String description, String dateDebut, String dateFin, String note,
            List<String> documents) {
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

    public List<String> getDocuments() {
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

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
}
