package mygroup.presentation.projet_detail;


import java.util.LinkedHashMap;
import java.util.List;

public class ProjectDetailModel {
    private LinkedHashMap<String, String> displayedTasks;
    private LinkedHashMap<String, String> displayedSeances;
    private LinkedHashMap<String, Boolean> displayedTasksEtat;
    private String ProjetID;


    private String title;
    private String description;
    private String categorie;
    private String type;
    private String dateDebut;
    private String dateFin;
    private List<String> taches;
    private List<String> documents;
    private List<String> seances;

    public ProjectDetailModel(String title, String description, String categorie, String type, String dateDebut,
    String dateFin, List<String> taches, List<String> documents, List<String> seances, LinkedHashMap<String, String> displayedTasks, LinkedHashMap<String, String> displayedSeances) {
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.taches = taches;
        this.documents = documents;
        this.seances = seances;
        this.displayedTasks = new LinkedHashMap<>();
        this.displayedTasksEtat = new LinkedHashMap<>();
        this.displayedTasks = displayedTasks;
        this.displayedSeances = displayedSeances;
    }

    public ProjectDetailModel() {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public List<String> getTaches() {
        return taches;
    }

    public void setTaches(List<String> taches) {
        this.taches = taches;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public void ajouterTache(String tache) {
        this.taches.add(tache);
    }

    public void ajouterDocument(String document) {
        this.documents.add(document);
    }

    public void ajouterSeance(String seance) {
        this.seances.add(seance);
    }

    public String getProjetID() {
        return ProjetID;
    }

    public void setProjetID(String ProjetID) {
        this.ProjetID = ProjetID;
    }

    public void setDisplayedTasks(LinkedHashMap<String, String> displayedTasks) {
        this.displayedTasks = displayedTasks;
    }

    public LinkedHashMap<String, String> getDisplayedTasks() {
        return displayedTasks;
    }

    public void addTaskEtat(String tacheId, Boolean etat) {
        this.displayedTasksEtat.put(tacheId, etat);
    }

    public void putInGridInfoCase(int rowCount, String key) {
        this.displayedTasks.put(key, key);
    }

    public void setDisplayedSeances(LinkedHashMap<String, String> seanceMap) {
        this.displayedSeances = seanceMap;
    }

    public LinkedHashMap<String, String> getDisplayedSeances() {
        return displayedSeances;
    }

    public void addTask(String tacheId, String titre) {
        this.displayedTasks.put(tacheId, titre);
    }


}
