package presentation.GetTaskFromCalendar;

import com.google.api.services.calendar.Calendar.Acl.Get;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import metier.GoogleCalendarService;

public class GetTaskCalenderController {
    private String dateTask;

    public GetTaskCalenderController(String dateTask) {
         this.dateTask = dateTask;
    }

    public void handleConfirmButton(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleConfirmButton'");
    }

    public void handleCancelButtonAction(ActionEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCancelButtonAction'");
    }

    public void displayTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayTasks'");
    }

    public ObservableList<Item> getDataTasks() {
        GetTaskCalendarModel model = new GetTaskCalendarModel();
        ObservableList<Item> data = model.getDataFromGoogle(this.dateTask);
        return data;
    }
    
}
