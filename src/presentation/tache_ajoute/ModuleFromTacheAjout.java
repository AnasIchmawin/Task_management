package presentation.tache_ajoute;

import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;

public class ModuleFromTacheAjout {
    private Integer id;
    private String titre;
    private String categorie;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private List<String> titreSelectionnes;
    private Document projet;
    private Document liste;



    public ModuleFromTacheAjout(Integer id ,String titre, String categorie, String type, String description, LocalDateTime dateDebut, LocalDateTime dateFin, List<String> titreSelectionnes,Document projet ,Document liste ) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.titreSelectionnes = titreSelectionnes;
        this.projet = projet;
        this.liste =liste;

    }
    public Integer getid(){return id;}

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public List<String> getTitreSelectionnes() {
        return titreSelectionnes;
    }

    public Document getProjet() {
        return projet;
    }

    public Document getListe() {
        return liste;
    }

    public void setProjet(Document projet) {
        this.projet = projet;
    }

    public void setListe(Document liste) {
        this.liste = liste;
    }

    public void setid(Integer id) {this.id = id ;}

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setTitreSelectionnes(List<String> titreSelectionnes) {
        this.titreSelectionnes = titreSelectionnes;
    }
}

