package mygroup.metier.POJO ;

import java.util.List;

public class POJOTache {

    private String titre;
    private String categorie;
    private Boolean etat;
    private String description;
    private String dateDebut;
    private String TempsDebut;
    private String dateFin;
    private String TempsFin;
    private List<String> documents;
    private String projet;
    private String liste;

    public POJOTache() {
        super();
    }
                    
    public POJOTache(String titre,Boolean etat ,String categorie, String description, String dateDebut, String TempsDebut,
                     String dateFin, String TempsFin, List<String> documents, String projet, String liste)
                     {
                        
        this.titre = titre;
        this.etat = etat;
        this.categorie = categorie;
        this.description = description;
        this.dateDebut = dateDebut;
        this.TempsDebut = TempsDebut;
        this.dateFin = dateFin;
        this.TempsFin = TempsFin;
        this.documents = documents;
        this.projet = projet;
        this.liste =liste;
    }

    public String gettitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getetat() {
        return etat;
    }

    public void setetat(Boolean etat) {
        this.etat = etat;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTempsDebut() {
        return TempsDebut;
    }

    public void setTempsDebut(String tempsDebut) {
        this.TempsDebut = tempsDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getTempsFin() {
        return TempsFin;
    }

    public void setTempsFin(String tempsFin) {
        this.TempsFin = tempsFin;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public String getprojet() {
        return projet;
    } 

    public String getliste() {
        return liste;
    } 

    public void setprojet(String projet) {
        this.projet = projet;
    }

    public void setliste(String liste){
        this.liste = liste;
    }
    @Override
public String toString() {
    return "POJOTache [titre=" + titre  +
            ", etat=" + etat +
            ", categorie=" + categorie  +
            ", description=" + description +
            ", dateDebut=" + dateDebut +
            ", TempsDebut=" + TempsDebut  +
            ", dateFin=" + dateFin +
            ", TempsFin=" + TempsFin +
            ", documents=" + documents +
            ", projet=" + projet  +
            ", liste=" + liste  +
            "]";
}
//mod

    public void setprojetID(String lastProjetId) {
        projet = lastProjetId;
    }

}