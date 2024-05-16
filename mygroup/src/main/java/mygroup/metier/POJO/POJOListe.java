package mygroup.metier.POJO ;

import java.util.List;

public class POJOListe {
    private String titre;
    private String description;
    private List<String> Taches;

    public POJOListe() {
    }

    public POJOListe(String titre, String description, List<String> taches) {
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

    public List<String> getTaches() {
        return Taches;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaches(List<String> taches) {
        Taches = taches;
    }

    @Override
    public String toString() {
        return "POJOListe [titre=" + titre + ", description=" + description + ", Taches=" + Taches + "]";
    }

}
