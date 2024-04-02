package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;

public class seance {
    private static int count = 0; //will be changed (store it in database)
    private int id;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String note;
    private List<document> documents;
    private projet projet;

    public seance(String description, LocalDateTime dateDebut, LocalDateTime dateFin, String note,
            List<document> documents, projet projet) {
        this.id = ++count;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.note = note;
        this.documents = documents;
        this.projet = projet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getId() {
        return id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public projet getProjet() {
        return projet;
    }

    public void setProjet(projet projet) {
        this.projet = projet;
    }

}
