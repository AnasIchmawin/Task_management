package presentation.NewDocument;

public class DocumentModel {
    private String titre;
    private String url;
    private String description;

    public DocumentModel(String titre, String url, String description) {
        this.titre = titre;
        this.url = url;
        this.description = description;
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
}
