package mygroup.metier.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.TasksScopes;
import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.TaskList;
import com.google.api.services.tasks.model.TaskLists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mygroup.presentation.GetTaskFromCalendar.Item;
import com.google.api.client.util.DateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class CalendarQuickstart {
    private static final String APPLICATION_NAME = "Task-Management";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @SuppressWarnings("unused")
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES_CALENDAR = Arrays.asList(CalendarScopes.CALENDAR_READONLY);
    private static final List<String> SCOPES_TASKS = Arrays.asList(TasksScopes.TASKS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, List<String> scopes)
            throws IOException {
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes)
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static ObservableList<Item> getEvents(NetHttpTransport HTTP_TRANSPORT, String date)
            throws IOException, GeneralSecurityException {
        Credential credential = getCredentials(HTTP_TRANSPORT, SCOPES_CALENDAR);
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> eventItems = events.getItems();
        ObservableList<Item> items = FXCollections.observableArrayList();
        if (!eventItems.isEmpty()) {
            for (Event event : eventItems) {
                @SuppressWarnings("unused")
                String title = event.getSummary();
                String startDate = getFormattedDateTime(event.getStart());
                String endDate = getFormattedDateTime(event.getEnd());
                if (startDate.contains(date) || endDate.contains(date)) {
                    System.out.println("valid date");
                    // items.add(new Item(false, title, startDate, endDate));
                }
            }
        }
        return items;
    }

    public static ObservableList<Item> getTasks(NetHttpTransport HTTP_TRANSPORT, String date)
            throws IOException, GeneralSecurityException {

        // Obtain the credentials
        Credential credential = getCredentials(HTTP_TRANSPORT, SCOPES_TASKS);

        // Build the tasks service
        Tasks tasksService = new Tasks.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Fetch the list of task lists
        TaskLists taskLists = tasksService.tasklists().list().execute();
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (TaskList taskList : taskLists.getItems()) {
            com.google.api.services.tasks.model.Tasks tasks = tasksService.tasks().list(taskList.getId()).execute();
            for (Task task : tasks.getItems()) {
                String title = task.getTitle();
                String description = task.getNotes();
                String dueDate = getFormattedDateTime(task.getDue());
                // if (dueDate != null && dueDate.contains(date)) {
                System.out.println("Valid date: " + dueDate);
                items.add(new Item(false, title, description, dueDate, dueDate));
            }
        }
        // }

        return items;
    }

    private static String getFormattedDueDate(DateTime dueDate) {
        if (dueDate == null) {
            return "";
        }

        long milliseconds = dueDate.getValue();
        Date date = new Date(milliseconds);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // Remplacez "GMT" par votre fuseau horaire

        return sdf.format(date);
    }

    private static String getFormattedDateTime(EventDateTime eventDateTime) {
        if (eventDateTime == null) {
            return "";
        }
        String dateTimeString = (eventDateTime.getDateTime() != null) ? eventDateTime.getDateTime().toString()
                : eventDateTime.getDate().toString();
        String[] dateTimeParts = dateTimeString.split("T");
        String[] dateParts = dateTimeParts[0].split("-");
        String[] timeParts = dateTimeParts[1].split(":");
        return dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0] + " " + timeParts[0] + ":" + timeParts[1];
    }

    @SuppressWarnings("unused")
    private static String getFormattedDateTime(DateTime taskDateTime) {
        if (taskDateTime == null) {
            return "";
        }
        String dateTimeString = (taskDateTime != null) ? taskDateTime.toString()
                : taskDateTime.toString();
        String[] dateTimeParts = dateTimeString.split("T");
        String[] dateParts = dateTimeParts[0].split("-");
        String[] timeParts = dateTimeParts[1].split(":");
        return dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0] + " " + timeParts[0] + ":" + timeParts[1];
    }
}
