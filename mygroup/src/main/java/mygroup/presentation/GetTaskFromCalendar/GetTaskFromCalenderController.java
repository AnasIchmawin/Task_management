package mygroup.presentation.GetTaskFromCalendar;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.metier.POJO.POJOTache;

public class GetTaskFromCalenderController {
    private String dateTask;
    private GestionnaireTache gestionnaireTache;
    private ObservableList<ItemTask> listTasItems;
    private GetTaskFromCalendar getTaskCalendar;

    public GetTaskFromCalenderController(GetTaskFromCalendar getTaskCalendar) {
        this.gestionnaireTache = new GestionnaireTache();
        this.getTaskCalendar = getTaskCalendar;
        this.dateTask = this.getTaskCalendar.getDateTask();
    }

    public void handleConfirmButton(ActionEvent event) {
        System.out.println("Confirm Button Clicked");
        System.out.println("Selected Tasks : ");
        for (ItemTask item : listTasItems) {
            if (item.isSelected()) {
                try {
                    POJOTache tache = new POJOTache();
                    tache.setTitre(item.getTitle());
                    tache.setDescription(item.getDescription());
                    tache.setDateDebut(item.getStartDate());
                    this.gestionnaireTache.setTache(tache);
                    gestionnaireTache.createTacheCalendar() ;
                    alert("Tâche ajoutée", "La tâche a été ajoutée avec succès");
                    this.handleCancelButtonAction(event);
                } catch (Exception e) {
                    alert("Erreur", "Une erreur s'est produite lors de l'ajout de la tâche");
                    e.printStackTrace();
                }
            }
        }

    }

    public void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public ObservableList<ItemTask> getDataTasks() {
        GetTaskFromCalendarModel model = new GetTaskFromCalendarModel();
        listTasItems = model.getDataFromGoogle(this.dateTask);
        return listTasItems;
    }

    public LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

      public void alert(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();

        // Créer une pause de 1 secondes avant de fermer l'alerte
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> alert.close()); // Utilise close() pour fermer l'alerte
        delay.play();
    }

}
