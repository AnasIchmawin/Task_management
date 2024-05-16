package metier.POJO;

import java.util.ArrayList;
import java.util.List;


import presentation.NewProjet.AddProjetModel;

public class POJOProjet {
    private String titre;
    private String description;
    private String Categorie;
    private String Type;
    private String DateDebut;
    private String DateFin;
    private List<String> Taches;
    private List<String> Seances;

    public POJOProjet() {
    }

    public POJOProjet(AddProjetModel addProjetModel) {
        this.titre = addProjetModel.getTitre();
        this.description = addProjetModel.getDescription();
        this.Categorie = addProjetModel.getCategorie();
        this.Type = addProjetModel.getType();
        this.DateDebut = addProjetModel.getDateDebut();
        this.DateFin = addProjetModel.getDateFin();
        this.Taches = new ArrayList<>(addProjetModel.getTachesSelectionnees().keySet());
        this.Seances = new ArrayList<>(addProjetModel.getSeancesSelectionnees().keySet());
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

    public String getCategorie() {
        return Categorie;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public String getType() {
        return Type;
    }

    public List<String> getSeances() {
        return Seances;
    }

    @Override
    public String toString() {
        return "POJOListe [titre=" + titre + ", description=" + description + ", Taches=" + Taches + "]";
    }
}
