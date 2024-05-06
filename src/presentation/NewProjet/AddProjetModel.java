package presentation.NewProjet;

import java.util.List;

public class AddProjetModel {
    private String titre;
    private String categorie;
    private String type;
    private String description;
    private String dateDebut;
    private String dateFin;
    private List<String> titreSelectionnes;

    public AddProjetModel(String titre, String categorie, String type, String description, String dateDebut, String dateFin, List<String> titreSelectionnes) {
        this.titre = titre;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.titreSelectionnes = titreSelectionnes;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public List<String> getTitreSelectionnes() {
        return titreSelectionnes;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setTitreSelectionnes(List<String> titreSelectionnes) {
        this.titreSelectionnes = titreSelectionnes;
    }
}
