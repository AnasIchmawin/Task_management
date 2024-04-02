package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;

public class tache {
    private static int count = 0; //will be changed (store it in database)
    private int id;
    private String categorie;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<document> documents;
    private projet projet;
    private liste liste;

    public tache(String categorie, String description, LocalDateTime dateDebut, LocalDateTime dateFin,
            List<document> documents, persistence.DAO.projet projet, persistence.DAO.liste liste) {
        this.id = ++count;
        this.categorie = categorie;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.documents = documents;
        this.projet = projet;
        this.liste = liste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<document> documents) {
        this.documents = documents;
    }

    public projet getProjet() {
        return projet;
    }

    public void setProjet(projet projet) {
        this.projet = projet;
    }

    public liste getListe() {
        return liste;
    }

    public void setListe(liste liste) {
        this.liste = liste;
    }

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }
    
}
