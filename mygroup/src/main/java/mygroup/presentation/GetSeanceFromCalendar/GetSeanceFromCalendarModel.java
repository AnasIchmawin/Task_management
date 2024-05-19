package mygroup.presentation.GetSeanceFromCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import javafx.collections.ObservableList;
import mygroup.metier.Service.CalendarQuickstart;
public class GetSeanceFromCalendarModel {
    private ObservableList<ItemSeance> data;

    public GetSeanceFromCalendarModel() {
    }

    public GetSeanceFromCalendarModel(ObservableList<ItemSeance> data) {
        this.data = data;
    }

    public ObservableList<ItemSeance> getData() {
        return data;
    }

    public void setData(ObservableList<ItemSeance> data) {
        this.data = data;
    }

    public void addSeance(ItemSeance seance) {
        data.add(seance);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ObservableList<ItemSeance> getDataFromGoogle(String myDate) {
        try {
            new CalendarQuickstart();
            System.out.println("Service created");
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            ObservableList data = CalendarQuickstart.getEvents(HTTP_TRANSPORT, myDate) ;
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
