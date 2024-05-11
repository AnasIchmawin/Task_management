package metier;

import java.time.LocalDateTime;

public class POJODocument {
    private String description;
    private String URL;
    private LocalDateTime dateAjout;
    private String Titre;

    public POJODocument() {
    }

    public POJODocument(String titre, String description, String URL, LocalDateTime dateAjout) {
        this.Titre = titre;
        this.description = description;
        this.URL = URL;
        this.dateAjout = dateAjout;
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

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
}