package metier;

public class POJODocument {
    private String description;
    private String URL;
    private String Titre;

    public POJODocument() {
    }

    public POJODocument(String titre, String description, String URL) {
        this.Titre = titre;
        this.description = description;
        this.URL = URL;
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

}