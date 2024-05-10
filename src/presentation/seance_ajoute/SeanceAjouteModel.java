package presentation.seance_ajoute;

import java.util.List;

/**
 * SeanceModel
 */
public class SeanceAjouteModel {
    String titre  ;
    String description ;
    String date_debut ;
    String time_debut ;
    String time_fin ;
    String date_fin ;
    List<String> documemtsList ;
    public SeanceAjouteModel(String titre, String description, String date_debut, String time_debut, String time_fin,
            String date_fin, List<String> documemtsList) {
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.time_debut = time_debut;
        this.time_fin = time_fin;
        this.date_fin = date_fin;
        this.documemtsList = documemtsList;
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
    public String getDate_debut() {
        return date_debut;
    }
    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }
    public String getTime_debut() {
        return time_debut;
    }
    public void setTime_debut(String time_debut) {
        this.time_debut = time_debut;
    }
    public String getTime_fin() {
        return time_fin;
    }
    public void setTime_fin(String time_fin) {
        this.time_fin = time_fin;
    }
    public String getDate_fin() {
        return date_fin;
    }
    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
    public List<String> getDocumemtsList() {
        return documemtsList;
    }
    public void setDocumemtsList(List<String> documemtsList) {
        this.documemtsList = documemtsList;
    }

    
}
