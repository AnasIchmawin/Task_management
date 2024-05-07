package metier;

import java.util.ArrayList;
import java.util.List;

import presentation.NewList.AddListModel;

public class POJOListe {
    private String titre;
    private String description;
    private List<String> Taches;

    public POJOListe() {
    }

    public POJOListe(AddListModel addListModel) {
        this.titre = addListModel.getTitre();
        this.description = addListModel.getDescription();
        this.Taches = new ArrayList<>(addListModel.getTachesSelectionnees().keySet());
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
