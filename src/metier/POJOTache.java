package metier;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

public class POJOTache {
    private Integer id;
    private String titre;
    private String categorie;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<Document> documents;
    private Document projet;
    private Document liste;

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

    public Document getListe() {
        return liste;
    }

    public void setListe(Document liste) {
        this.liste = liste;
    }

    @Override
    public String toString() {
        return "POJOTache [id=" + id + ", titre=" + titre + ", categorie=" + categorie + ", description=" + description
                + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", documents=" + documents + ", projet="
                + projet + ", liste=" + liste + "]";
    }
}
