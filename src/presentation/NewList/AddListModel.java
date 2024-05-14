package presentation.NewList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AddListModel {
    private String Titre;
    private String Description;
    private LinkedHashMap<String, String> TachesSelectionnees;

    public AddListModel(String titre, String description, LinkedHashMap<String, String> tachesSelectionnees) {
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

    public List<String> getTasksTitles() {
        return new ArrayList<>(getTachesSelectionnees().values());
    }

    public void addList(String titre, String Description, LinkedHashMap<String, String> tachesSelectionnees) {
        this.Titre = titre;
        this.Description = Description;
        this.TachesSelectionnees = tachesSelectionnees;
    }
}
