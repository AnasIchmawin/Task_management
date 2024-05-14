package presentation.GetTaskFromCalendar;

import javafx.collections.ObservableList;
import metier.Service.GoogleCalendarService;

public class GetTaskCalendarModel {
    private ObservableList<Item> data;

    public GetTaskCalendarModel() {
    }

    public GetTaskCalendarModel(ObservableList<Item> data) {
        this.data = data;
    }

    public ObservableList<Item> getData() {
        return data;
    }

    public void setData(ObservableList<Item> data) {
        this.data = data;
    }

    public void addTask(Item task) {
        data.add(task);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ObservableList<Item> getDataFromGoogle(String myDate) {
        try {
            GoogleCalendarService service = new GoogleCalendarService();
            System.out.println("Service created");
            ObservableList data = service.getDataEvents(myDate);
            System.out.println("Data retrieved");
            System.out.println(data);
            setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
}
