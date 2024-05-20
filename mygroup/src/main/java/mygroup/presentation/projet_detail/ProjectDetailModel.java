package mygroup.presentation.projet_detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class ProjectDetailModel {
    private LinkedHashMap<String, String> displayedTasks;
    private LinkedHashMap<String, String> displayedSeances;
    private LinkedHashMap<String, Boolean> displayedTasksEtat;
    private LinkedHashMap<List<List<String>>, String> gridInfoCase;
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
    private String seanceClicked;

    public ProjectDetailModel(String title, String description, String categorie, String type, String dateDebut,
            String dateFin, List<String> taches, List<String> documents, List<String> seances,
            LinkedHashMap<String, String> displayedTasks, LinkedHashMap<String, String> displayedSeances) {
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
        this.gridInfoCase = new LinkedHashMap<>();
    }

    public ProjectDetailModel() {
        displayedTasks = new LinkedHashMap<>();
        displayedSeances = new LinkedHashMap<>();
        displayedTasksEtat = new LinkedHashMap<>();
        taches = new ArrayList<>();
        documents = new ArrayList<>();
        seances = new ArrayList<>();
        gridInfoCase = new LinkedHashMap<>();

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

    public void putInGridInfoCase(Integer Row, Integer Column, String id) {
        List<List<String>> caseInfo = new ArrayList<>();
        caseInfo.add(Arrays.asList(Row.toString(), Column.toString()));
        gridInfoCase.put(caseInfo, id);
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

    public void addSeance(LinkedHashMap<String, String> SeanceMap) {
        String seanceId = SeanceMap.keySet().stream().findFirst().get();
        System.out.println("Seance ID: " + seanceId);
        System.out.println("Seance titre: " + SeanceMap.get(seanceId));
        this.displayedSeances.put(seanceId, SeanceMap.get(seanceId));
    }

 

    public LinkedHashMap<List<List<String>>, String> getGridInfoCase() {
        return gridInfoCase;
    }

    public void setGridInfoCase(LinkedHashMap<List<List<String>>, String> gridInfoCase) {
        this.gridInfoCase = gridInfoCase;
    }

    public String getSeanceClicked() {
        return seanceClicked;
    }

    public void setSeanceClicked(String seanceClicked) {
        this.seanceClicked = seanceClicked;
    }

   


}
