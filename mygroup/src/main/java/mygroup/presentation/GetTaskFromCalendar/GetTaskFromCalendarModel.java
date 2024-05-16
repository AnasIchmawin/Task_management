package mygroup.presentation.GetTaskFromCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;


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

    @SuppressWarnings("unchecked")
    public ObservableList<Item> getDataFromGoogle(String myDate) {
        try {
            new CalendarQuickstart();
            System.out.println("Service created");
            // NetHttpTransport
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            @SuppressWarnings("rawtypes")
            ObservableList data = CalendarQuickstart.getTasks(HTTP_TRANSPORT, myDate);
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
