package mygroup.metier.Gestionnaire ;
import mygroup.metier.POJO.POJOSeance;

import java.time.LocalDate;
import java.time.LocalTime;
import org.bson.Document;
import mygroup.metier.Errors.*;
import mygroup.persistence.DAO.DAOSeance;

import java.util.LinkedHashMap;
import java.util.List;

public class GestionnaireSeance {
    private DAOSeance daoSeance;
    private POJOSeance seance;

    public GestionnaireSeance() {
        this.daoSeance = new DAOSeance();
        this.seance = new POJOSeance();
    }

    public DAOSeance getDaoSeance() {
        return daoSeance;
    }

    public void setDaoSeance(DAOSeance daoSeance) {
        this.daoSeance = daoSeance;
    }

    public POJOSeance getSeance() {
        return seance;
    }

    public void setSeance(POJOSeance seance) {
        this.seance = seance;
    }

    public void createSeance() throws NonValidSeance {
        if (this.seance.getTitre() == null || this.seance.getDateDebut() == null || this.seance.getHeureDebut() == null
                || this.seance.getDateFin() == null || this.seance.getHeureFin() == null) {
            throw new NonValidSeance("Tous les champs de la séance doivent être remplis");
        }
        this.daoSeance.create(this.seance.getTitre(),
                this.seance.getDateDebut(),
                this.seance.getHeureDebut(),
                this.seance.getDateFin(),
                this.seance.getHeureFin(),
                this.seance.getDescription(),
                this.seance.getNote(),
                this.seance.getDocuments());
    }

   

    public static boolean validateDate(String date) {
        try {
            // Vérifie si la date est dans le futur
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    // Méthode pour valider une heure au format HH:MM
    public static boolean validateTime(String time) {
        try {
            @SuppressWarnings("unused")
            LocalTime parsedTime = LocalTime.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Exemple de méthode de validation complète pour une séance
    public static boolean validateSeance(String dateDebut, String heureDebut, String dateFin, String heureFin) {
        return validateDate(dateDebut) && validateDate(dateFin) &&
                validateTime(heureDebut) && validateTime(heureFin) &&
                LocalDate.parse(dateDebut).isBefore(LocalDate.parse(dateFin)); // Vérifie que dateDebut est avant
                                                                               // dateFin
    }

    public List<Document> getAllSeances() {
        return daoSeance.getAllSeances();
    }

    public LinkedHashMap<String, String> getLastSeance() {
        return daoSeance.getLastSeance();
    }
}
