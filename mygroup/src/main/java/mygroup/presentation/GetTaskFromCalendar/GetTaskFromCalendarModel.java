package mygroup.presentation.GetTaskFromCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;


import javafx.collections.ObservableList;
import mygroup.metier.Service.TaskQuickstart;

public class GetTaskFromCalendarModel {
    private ObservableList<ItemTask> data;

    public GetTaskFromCalendarModel() {
    }

    public GetTaskFromCalendarModel(ObservableList<ItemTask> data) {
        this.data = data;
    }

    public ObservableList<ItemTask> getData() {
        return data;
    }

    public void setData(ObservableList<ItemTask> data) {
        this.data = data;
    }

    public void addTask(ItemTask task) {
        data.add(task);
    }

    @SuppressWarnings("unchecked")
    public ObservableList<ItemTask> getDataFromGoogle(String myDate) {
        try {
            new TaskQuickstart();
            System.out.println("Service created");
            // NetHttpTransport
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            @SuppressWarnings("rawtypes")
            ObservableList data = TaskQuickstart.getTasks(HTTP_TRANSPORT, myDate);
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
