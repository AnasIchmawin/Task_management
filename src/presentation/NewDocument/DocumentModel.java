package presentation.NewDocument;

import java.time.LocalDate;

public class DocumentModel {
    private String titre;
    private String url;
    private String description;
    private LocalDate dateInsertion;
    private String idProjet;
    private String idTache;

    public DocumentModel() {
    }

    public DocumentModel(String titre, String url, String description, LocalDate dateInsertion, String idProjet, String idTache) {
        this.titre = titre;
        this.url = url;
        this.description = description;
        this.dateInsertion = dateInsertion;
        this.idProjet = idProjet;
        this.idTache = idTache;
    }

    public LocalDate getDateInsertion() {
        return dateInsertion;
    }

    public void setDateInsertion(LocalDate dateInsertion) {
        this.dateInsertion = dateInsertion;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(String idProjet) {
        this.idProjet = idProjet;
    }

    public String getIdTache() {
        return idTache;
    }

    public void setIdTache(String idTache) {
        this.idTache = idTache;
    }
}
