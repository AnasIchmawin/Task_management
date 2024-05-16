package presentation.NewProjet;

import java.util.LinkedHashMap;

public class AddProjetModel {
    private String Titre;
    private String Description;
    private String Categorie;
    private String Type;
    private String DateDebut;
    private String DateFin;
    private LinkedHashMap<String, String> TachesSelectionnees;
    private LinkedHashMap<String, String> SeancesSelectionnees;

    public AddProjetModel(String titre, String description, String categorie, String type, String dateDebut, String dateFin,
            LinkedHashMap<String, String> tachesSelectionnees, LinkedHashMap<String, String> seancesSelectionnees) {
        this.Titre = titre;
        this.Description = description;
        this.Categorie = categorie;
        this.Type = type;
        this.DateDebut = dateDebut;
        this.DateFin = dateFin;
        this.TachesSelectionnees = tachesSelectionnees;
        this.SeancesSelectionnees = seancesSelectionnees;
    }

    public String getTitre() {
        return Titre;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategorie() {
        return Categorie;
    }

    public String getType() {
        return Type;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public LinkedHashMap<String, String> getTachesSelectionnees() {
        return TachesSelectionnees;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDateDebut(String dateDebut) {
        DateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }

    public void setTachesSelectionnees(LinkedHashMap<String, String> tachesSelectionnees) {
        this.TachesSelectionnees = tachesSelectionnees;
    }

    public void addTask(String id, String titre) {
        TachesSelectionnees.put(id, titre);
    }

    public void addSeance(LinkedHashMap<String, String> seance) {
        SeancesSelectionnees.putAll(seance);
    }

    public LinkedHashMap<String, String> getSeancesSelectionnees() {
        return SeancesSelectionnees;
    }

}
