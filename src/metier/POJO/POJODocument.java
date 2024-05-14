package metier.POJO;


public class POJODocument {
    private String description;
    private String URL;
    private String dateAjout;
    private String Titre;
    private String idProjet;
    private String idTache;
    private String idSeance;

    public POJODocument() {
    }

    public POJODocument(String titre, String description, String URL, String dateAjout, String idProjet, String idTache, String idSeance) {
        this.Titre = titre;
        this.description = description;
        this.URL = URL;
        this.dateAjout = dateAjout;
        this.idProjet = idProjet;
        this.idTache = idTache;
        this.idSeance = idSeance;
    }

    // Getters and Setters
    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
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

    public String getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(String idSeance) {
        this.idSeance = idSeance;
    }

}