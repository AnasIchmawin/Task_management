package metier;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

public class POJOSeance {
    private Integer id;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private double note;
    private List<Document> documents;
    private Document projet;

    // Getters and Setters
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

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Document getProjet() {
        return projet;
    }

    public void setProjet(Document projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "POJOSeance [id=" + id + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin="
                + dateFin + ", note=" + note + ", documents=" + documents + ", projet=" + projet + "]";
    }
}
