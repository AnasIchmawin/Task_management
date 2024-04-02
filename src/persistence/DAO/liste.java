package persistence.DAO;

import java.util.List;

public class liste {
    private static int count = 0; //will be changed (store it in database)
    private int id;
    private String description;
    private List<tache> taches;

    public liste(String description, List<tache> taches) {
        this.id = ++count;
        this.description = description;
        this.taches = taches;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<tache> getTaches() {
        return taches;
    }

    public void setTaches(List<tache> taches) {
        this.taches = taches;
    }

}
