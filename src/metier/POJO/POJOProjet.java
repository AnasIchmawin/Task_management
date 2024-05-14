package metier.POJO;

import java.util.ArrayList;
import java.util.List;


import presentation.NewProjet.AddProjetModel;

public class POJOProjet {
    private String titre;
    private String description;
    private List<String> Taches;

    public POJOProjet() {
    }

    public POJOProjet(AddProjetModel addProjetModel) {
        this.titre = addProjetModel.getTitre();
        this.description = addProjetModel.getDescription();
        this.Taches = new ArrayList<>(addProjetModel.getTachesSelectionnees().keySet());
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTaches() {
        return Taches;
    }

    public void setTaches(List<String> taches) {
        Taches = taches;
    }

    @Override
    public String toString() {
        return "POJOListe [titre=" + titre + ", description=" + description + ", Taches=" + Taches + "]";
    }
}
