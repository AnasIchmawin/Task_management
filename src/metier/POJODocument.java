package metier;

import java.time.LocalDateTime;

public class POJODocument {
    private String id;
    private String titre;
    private String description;
    private String path;
    private LocalDateTime dateAjout;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    @Override
    public String toString() {
        return "POJODocument [id=" + id + ", titre=" + titre + ", description=" + description + ", path=" + path
                + ", dateAjout=" + dateAjout + "]";
    }
}
