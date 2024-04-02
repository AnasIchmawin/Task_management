package persistence.DAO;

import java.time.LocalDateTime;
import java.util.List;

public class projet {
    private static int count = 0; //will be changed (store it in database)
    private int id;
    private String categorie;
    private String type;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<seance> seances;
    private List<document> documents;
    private List<tache> taches;
    private boolean cloture;

    public projet(String categorie, String type, String description, LocalDateTime dateDebut, LocalDateTime dateFin,
            List<seance> seances, List<document> documents, List<tache> taches) {
        this.id = ++count;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.seances = seances;
        this.documents = documents;
        this.taches = taches;
        this.cloture = false;
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

    public List<seance> getSeances() {
        return seances;
    }

    public void setSeances(List<seance> seances) {
        this.seances = seances;
    }

    public List<document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<document> documents) {
        this.documents = documents;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public List<tache> getTaches() {
        return taches;
    }

    public void setTaches(List<tache> taches) {
        this.taches = taches;
    }

}
