package mygroup.metier.Gestionnaire ;
import mygroup.metier.POJO.POJOTache;

import org.bson.Document;

import mygroup.metier.Errors.*;
import mygroup.persistence.DAO.DAOTache;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//mod
public class GestionnaireTache {
    private DAOTache daoTache;
    private POJOTache pojoTache;

    public GestionnaireTache() {
        this.daoTache = new DAOTache();
        this.pojoTache = new POJOTache();
    }

    public DAOTache getDaoTache() {
        return daoTache;
    }

    public void setDaoTache(DAOTache daoTache) {
        this.daoTache = daoTache;
    }

    public POJOTache getTache() {
        return pojoTache;
    }

    public void setTache(POJOTache pojoTache) {
        this.pojoTache = pojoTache;
    }

    // Create
    public void createTache() throws NonValidTache {
        if (this.pojoTache.gettitre() == null || this.pojoTache.getDateDebut() == null || this.pojoTache.getTempsDebut() == null
                || this.pojoTache.getDateFin() == null || this.pojoTache.getTempsFin() == null) {
            throw new NonValidTache("Tous les champs de la tache  doivent être remplis");
        }

        if (!validateTache(this.pojoTache.getDateDebut(), this.pojoTache.getTempsDebut(), this.pojoTache.getDateFin(),
                this.pojoTache.getTempsFin())) {
            throw new NonValidTache("Les dates ou heures de la tache ne sont pas valides");
        }

        this.daoTache.create(
            this.pojoTache.gettitre(),
            this.pojoTache.getetat(),
            this.pojoTache.getCategorie(),
            this.pojoTache.getDescription(),
            this.pojoTache.getDateDebut(),
            this.pojoTache.getTempsDebut(),
            this.pojoTache.getDateFin(),
            this.pojoTache.getTempsFin(),
            this.pojoTache.getDocuments(),
            this.pojoTache.getprojet(),
            this.pojoTache.getliste());
    }
 
    public static boolean validateDate(String date) {
        try {
            // Vérifie si la date est dans le futur
            @SuppressWarnings("unused")
            LocalDate parsedDate = LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean validateTime(String time) {
        try {
            @SuppressWarnings("unused")
            LocalTime parsedTime = LocalTime.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateTache(String dateDebut, String tempsDebut, String dateFin, String tempsFin) {
        return validateDate(dateDebut) && validateDate(dateFin) &&
                validateTime(tempsDebut) && validateTime(tempsFin) &&
                LocalDate.parse(dateDebut).isBefore(LocalDate.parse(dateFin)); // Check if dateDebut is before dateFin
    }


    // Read
    public Document readTask(String id) {
        return daoTache.read(id);
    }

    // Update
    public void updateTask() {

        daoTache.update(
        this.pojoTache.gettitre(),
        this.pojoTache.getetat(),
        this.pojoTache.getCategorie(),
        this.pojoTache.getDescription(),
        this.pojoTache.getDateDebut(),
        this.pojoTache.getTempsDebut(),
        this.pojoTache.getDateFin(),
        this.pojoTache.getTempsFin(),
        this.pojoTache.getDocuments(),
        this.pojoTache.getprojet(),
        this.pojoTache.getliste());}
    

    // Delete
    public void deleteTask(String tacheId) {
        daoTache.delete(tacheId);
    }

    public void updateTask(String title, String description, String idTache) {
        daoTache.updateTask(title, description, idTache);
    }

    // GetAllTask
    public List<Document> getAllTasks() {
        return daoTache.getAllTaches();
    }

    // getTitle
    public String getTitle(String tacheId) {
        return daoTache.getTitre(tacheId);
    }

    // getDescription
    public String getDescription(String tacheId) {
        return daoTache.getDescription(tacheId);
    }

    // getStartDate
    public String getStartDate(String tacheId) {
        return daoTache.getDateDebut(tacheId);
    }

    // getEndDate
    public String getEndDate(String tacheId) {
        return daoTache.getDateFin(tacheId);
    }

    // get type
    public String getType(String tacheId) {
        return daoTache.getType(tacheId);
    }

    // get categorie
    public String getCategorie(String tacheId) {
        return daoTache.getCategorie(tacheId);
    }


    

    // getTaskEtat
    public Boolean getTaskEtat(String tacheId) {
        return daoTache.getEtat(tacheId);
    }

    

    // setTaskEtat
    public void setTaskEtat(String tacheId, Boolean etat) {
        daoTache.setEtat(tacheId, etat);
    }

    //setTaskListe
    public void setTaskListe(String listeId) {
        pojoTache.setliste(listeId);
    }

    //getLastTacheId
    public String getLastTacheId() {
        return daoTache.getLastTacheId();
    }

    public void cloneTask(String tacheId) {
        daoTache.cloneTask(tacheId);
    }

    public void createTacheCalendar() {
        try{
        daoTache.create(this.pojoTache.gettitre(), this.pojoTache.getetat(), this.pojoTache.getCategorie(),
                this.pojoTache.getDescription(), this.pojoTache.getDateDebut(), this.pojoTache.getTempsDebut(),
                this.pojoTache.getDateFin(), this.pojoTache.getTempsFin(), this.pojoTache.getDocuments(),
                this.pojoTache.getprojet(), this.pojoTache.getliste());
        }catch(Exception e){
            System.out.println("Erreur lors de la création de la tâche calendrier");
            e.printStackTrace();
        }
    }

    public void updateIdListForTask(String Task_ID , String List_ID){
        if(!Task_ID.isEmpty())
        this.daoTache.updateListId(Task_ID , List_ID) ;
      }

    public void setProjetId(List<String> ListTaskIds, String lastProjetId) {
        this.daoTache.setProjetId(ListTaskIds, lastProjetId);
    }

    public void addDocToTache(String taskSelectedId, String idLastDoc) {
        this.daoTache.addDocToTache(taskSelectedId, idLastDoc);
    }



}
