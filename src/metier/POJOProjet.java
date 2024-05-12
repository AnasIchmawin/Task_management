package metier;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import presentation.NewProjet.AddProjetModel;

public class POJOProjet {
    private String titre;
    private String description;
    private String categorie;
    private String type;
    private String dateDebut;
    private String dateFin;
    private List<String> Taches;

    public POJOProjet() {
    }

    public POJOProjet(AddProjetModel addProjetModel) {
        this.titre = addProjetModel.getTitre();
        this.description = addProjetModel.getDescription();
        this.Taches = new ArrayList<>(addProjetModel.getTachesSelectionnees().keySet());
        this.categorie = addProjetModel.getCategorie();
        this.type = addProjetModel.getType();
        this.dateDebut = addProjetModel.getDateDebut();
        this.dateFin = addProjetModel.getDateFin();
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

    public String getCategorie() {
        return categorie;
    }

    public String getType() {
        return type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
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
