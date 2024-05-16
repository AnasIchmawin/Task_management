package mygroup.presentation.GetTaskFromCalendar;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import mygroup.metier.Gestionnaire.GestionnaireTache;
import mygroup.metier.POJO.POJOTache;

public class GetTaskFromCalenderController {
    private String dateTask;
    private GestionnaireTache gestionnaireTache;
    private ObservableList<Item> listTasItems;
    private GetTaskFromCalendar getTaskCalendar;

    public GetTaskFromCalenderController(GetTaskFromCalendar getTaskCalendar) {
        this.gestionnaireTache = new GestionnaireTache();
        this.getTaskCalendar = getTaskCalendar;
        this.dateTask = this.getTaskCalendar.getDateTask();
    }

    public void handleConfirmButton(ActionEvent event) {
        System.out.println("Confirm Button Clicked");
        System.out.println("Selected Tasks : ");
        for (Item item : listTasItems) {
            if (item.isSelected()) {
                try {
                    POJOTache tache = new POJOTache();
                    tache.setTitre(item.getTitle());
                    tache.setDescription(item.getDescription());
                    tache.setDateDebut(item.getStartDate());
                    tache.setDateFin(item.getEndDate());
                    this.gestionnaireTache.setTache(tache);
                    gestionnaireTache.createTache();

                } catch (Exception e) {
                    System.out.println("Erreur lors de la création de la tâche");
                    e.printStackTrace();
                }
            }
        }

    }

    public void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public ObservableList<Item> getDataTasks() {
        GetTaskFromCalendarModel model = new GetTaskFromCalendarModel();
        listTasItems = model.getDataFromGoogle(this.dateTask);
        return listTasItems;
    }

    public LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

}
