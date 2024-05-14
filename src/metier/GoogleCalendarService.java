// GoogleCalendarService.java
package metier;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.GetTaskFromCalendar.Item;
import java.awt.Desktop;

public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "Task-Management";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static String code;

    private static Credential authorize() throws Exception {
        InputStream in = GoogleCalendarService.class.getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl()
                .setRedirectUri("http://localhost:8000");
        URI uri = new URI(authorizationUrl.build());
        Desktop.getDesktop().browse(uri);
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String query = exchange.getRequestURI().getQuery();
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                    String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                    if (key.equals("code")) {
                        code = value;
                        break;
                    }
                }

                String response = "Vous pouvez maintenant fermer cette fenêtre.";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        server.start();

        while (code == null) {
            Thread.sleep(100);
        }

        GoogleTokenResponse response = flow.newTokenRequest(code) //////////////////////////////////////////////////////////////////////////
                .setRedirectUri("http://localhost:8000")
                .execute();


        server.stop(0);

        return flow.createAndStoreCredential(response, "user");
    }

    public static List<Event> getEvents() throws IOException, Exception {
        Credential credential = authorize();
        Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems();
    }

    public ObservableList<Item> getDataEvents(String mydate) {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            List<Event> events = getEvents();
            if (!events.isEmpty()) {
                for (Event event : events) {
                    String title = event.getSummary();
                    String startDate = getFormattedDateTime(event.getStart());
                    String endDate = getFormattedDateTime(event.getEnd());
                    String description = event.getDescription();
                    System.out.println("Title: " + title);
                    System.out.println("Start Date: " + startDate);
                    if (startDate.contains(mydate) || endDate.contains(mydate))
                        items.add(new Item(false, title, description, startDate, endDate));
                }
            }
        } catch (IOException e) {
            handleException("IOException", e);
        } catch (Exception e) {
            handleException("Exception", e);
        }
        return items;
    }

    private String getFormattedDateTime(EventDateTime eventDateTime) {
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

    private void handleException(String type, Exception e) {
        System.out.println(type);
        e.printStackTrace();
    }

}
