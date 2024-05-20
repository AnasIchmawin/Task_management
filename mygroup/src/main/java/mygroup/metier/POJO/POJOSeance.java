package mygroup.metier.POJO ;

import java.util.List;

import org.bson.Document;

public class POJOSeance {
    private String titre;
    private String dateDebut;
    private String heureDebut;
    private String dateFin;
    private String heureFin;
    private String description;
    private String note;
    private List<String> documents;

    public POJOSeance() {
        super() ;
    }


    public POJOSeance(String titre, String dateDebut, String heureDebut, String dateFin, String heureFin,
            String description, String note, List<String> documents) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.heureDebut = heureDebut;
        this.dateFin = dateFin;
        this.heureFin = heureFin;
        this.description = description;
        this.note = note;
        this.documents = documents;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
    public String getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public String getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
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
    public List<String> getDocuments() {
        return documents;
    }
    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
    @Override
    public String toString() {
        return "POJOSeance [titre=" + titre + ", dateDebut=" + dateDebut + ", heureDebut=" + heureDebut + ", dateFin="
                + dateFin + ", heureFin=" + heureFin + ", description=" + description + ", note=" + note
                + ", documents=" + documents + "]";
    }


    

}
