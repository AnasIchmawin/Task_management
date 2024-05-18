package mygroup.presentation.GetSeanceFromCalendar;

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
import mygroup.metier.Gestionnaire.GestionnaireSeance;
import mygroup.metier.POJO.POJOSeance;

public class GetSeanceFromCalenderController {
    private String dateSeance;
    private GestionnaireSeance gestionnaireSeance;
    private ObservableList<ItemSeance> listTasItems;
    private GetSeanceFromCalendar getSeanceFromCalendar;

    public GetSeanceFromCalenderController(GetSeanceFromCalendar getSeanceFromCalendar) {
        this.gestionnaireSeance = new GestionnaireSeance();
        this.getSeanceFromCalendar = getSeanceFromCalendar;
        this.dateSeance = this.getSeanceFromCalendar.getDateSeance();
    }

    public void handleConfirmButton(ActionEvent event) {
        System.out.println("Confirm Button Clicked");
        System.out.println("Selected events : ");
        for (ItemSeance item : listTasItems) {
            if (item.isSelected()) {
                try {
                    POJOSeance seance = new POJOSeance();
                    seance.setTitre(item.getTitle());
                    seance.setDescription(item.getDescription());
                    seance.setDateDebut(item.getStartDate());
                    seance.setDateFin(item.getEndDate());
                    this.gestionnaireSeance.setSeance(seance);
                    gestionnaireSeance.createSeance();
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

    public ObservableList<ItemSeance> getDataSeance() {
        GetSeanceFromCalendarModel model = new GetSeanceFromCalendarModel();
        listTasItems = model.getDataFromGoogle(this.dateSeance);
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
