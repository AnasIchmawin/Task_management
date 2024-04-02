package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;


public class document {
    private static int count = 0; //will be changed (store it in database)
    private int id;
    private String description;
    private String path;
    private LocalDateTime dateAjout;
    private List<projet> projets;
    private List<seance> seances;

    
    public document(String description, String path, LocalDateTime dateAjout, List<projet> projets,
            List<seance> seances) {
        this.id = ++count;
        this.description = description;
        this.path = path;
        this.dateAjout = dateAjout;
        this.projets = projets;
        this.seances = seances;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public List<projet> getProjets() {
        return projets;
    }

    public void setProjets(List<projet> projets) {
        this.projets = projets;
    }

    public List<seance> getSeances() {
        return seances;
    }

    public void setSeances(List<seance> seances) {
        this.seances = seances;
    }

}
