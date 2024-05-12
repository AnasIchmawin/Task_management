package metier;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;



public class POJOTache {
    private String titre;
    private String categorie;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<String> documents;
    private Document projet;
    private Document liste;
    private Boolean etat;



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

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }


}
