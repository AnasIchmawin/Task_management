package mygroup.presentation.GetTaskFromCalendar;

// import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
// import com.google.api.client.http.javanet.NetHttpTransport;

import javafx.collections.ObservableList;
import mygroup.metier.Service.CalendarQuickstart;

public class GetTaskFromCalendarModel {
    private ObservableList<Item> data;

    public GetTaskFromCalendarModel() {
    }

    public GetTaskFromCalendarModel(ObservableList<Item> data) {
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


    public ObservableList<Item> getDataFromGoogle(String myDate) {
        try {
            @SuppressWarnings("unused")
            CalendarQuickstart service = new CalendarQuickstart();
            System.out.println("Service created");
         //   ObservableList data = service.getEvents(myDate);
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
