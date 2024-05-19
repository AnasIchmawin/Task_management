
package mygroup.metier.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mygroup.presentation.GetSeanceFromCalendar.ItemSeance;
import com.google.api.client.util.DateTime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class CalendarQuickstart {
    private static final String APPLICATION_NAME = "Task-Management";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String DATA_STORE_DIR = "tokens1";
    private static final List<String> SCOPES_CALENDAR = Arrays.asList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, List<String> scopes)
            throws IOException {
        InputStream in = TaskQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        java.io.File dataStoreDir = new java.io.File(DATA_STORE_DIR);
        System.out.println(dataStoreDir.getAbsolutePath());
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(dataStoreDir);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        try {
            // Try to refresh the token
            if (credential.refreshToken()) {
                System.out.println("Token refreshed successfully.");
            } else {
                throw new IOException("Token refresh failed.");
            }
        } catch (IOException e) {
            // Redirect to the browser to get new permissions
            credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        }

        return credential;
    }

    public static boolean hasPermissions(NetHttpTransport HTTP_TRANSPORT, List<String> scopes)
            throws IOException {
        Credential credential = getCredentials(HTTP_TRANSPORT, scopes);
        if (credential != null && credential.refreshToken()) {
            return true;
        }
        return false;
    }

    public static ObservableList<ItemSeance> getEvents(NetHttpTransport HTTP_TRANSPORT, String date)
            throws IOException, GeneralSecurityException {
        if (!hasPermissions(HTTP_TRANSPORT, SCOPES_CALENDAR)) {
            // Rediriger l'utilisateur vers le navigateur pour obtenir les autorisations
            return null;
        }

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
        ObservableList<ItemSeance> items = FXCollections.observableArrayList();
        if (!eventItems.isEmpty()) {
            for (Event event : eventItems) {
                String title = event.getSummary();
                String description = event.getDescription();
                String startDate = getFormattedDateTime(event.getStart());
                String endDate = getFormattedDateTime(event.getEnd());
                if (startDate.contains(date) || endDate.contains(date)) {
                    items.add(new ItemSeance(false, title, description, startDate, endDate));
                }
            }
        }
        return items;
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

    public static void clearTokenContent() {
        // Chemin vers le répertoire de stockage des données du token
        String tokenDirPath = DATA_STORE_DIR;
        File tokenDir = new File(tokenDirPath);
        
        // Vérifiez si le répertoire existe
        if (tokenDir.exists() && tokenDir.isDirectory()) {
            // Supprimez tous les fichiers du répertoire
            File[] files = tokenDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
    
}
