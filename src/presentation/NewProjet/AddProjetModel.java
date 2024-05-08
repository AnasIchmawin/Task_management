package presentation.NewProjet;

import java.util.LinkedHashMap;

public class AddProjetModel {
    private String Titre;
    private String Description;
    private LinkedHashMap<String, String> TachesSelectionnees;

    public AddProjetModel(String titre, String description, LinkedHashMap<String, String> tachesSelectionnees) {
        Titre = titre;
        Description = description;
        this.TachesSelectionnees = tachesSelectionnees;
    }

    public String getTitre() {
        return Titre;
    }

    public String getDescription() {
        return Description;
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

    public void setTachesSelectionnees(LinkedHashMap<String, String> tachesSelectionnees) {
        this.TachesSelectionnees = tachesSelectionnees;
    }

    public void addTask(String id, String titre) {
        TachesSelectionnees.put(id, titre);
    }
}
