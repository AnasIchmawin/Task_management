package metier;

import java.util.List;

import org.bson.Document;

public class POJOListe {
    private String titre ;
    private String description ;
    private List<Document> Taches ;

    public POJOListe() {
    }
    public POJOListe(String titre, String description, List<Document> taches) {
        this.titre = titre;
        this.description = description;
        Taches = taches;
        
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public List<Document> getTaches() {
        return Taches;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTaches(List<Document> taches) {
        Taches = taches;
    }
    @Override
    public String toString() {
        return "POJOListe [titre=" + titre + ", description=" + description + ", Taches=" + Taches + "]";
    }
    

}
