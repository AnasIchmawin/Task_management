package mygroup.presentation.projet_detail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetail {
    // private String ProjetID;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String category;
    private String type;
    private List<String> tasks;
    private List<String> documents;
    private List<String> sessions;

    public ProjectDetail(String title, String description, String startDate, String endDate, String category, String type) {
        // this.ProjetID= ProjetID;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.type = type;
        this.tasks = new ArrayList<>();
        this.documents = new ArrayList<>();
        this.sessions = new ArrayList<>();
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public void addDocument(String document) {
        this.documents.add(document);
    }

    public void addSession(String session) {
        this.sessions.add(session);
    }

    // public String getProjetID() {
        // return ProjetID;
    // }

    // public void setProjetID(String ProjetID) {
        // this.ProjetID = ProjetID;
    // }


}
