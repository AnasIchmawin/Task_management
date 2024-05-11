package metier;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.awt.*;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "Task-Management";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    private static Credential authorize() throws Exception {
        // Load client secrets from client_secret.json file downloaded from Google Cloud
        // Console
        InputStream in = GoogleCalendarService.class.getResourceAsStream("client_secret1.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        // Generate authorization URL and open browser to it
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl()
                .setRedirectUri("urn:ietf:wg:oauth:2.0:oob");
        URI uri = new URI(authorizationUrl.build());
        Desktop.getDesktop().browse(uri);
        System.out.println("A browser window should have opened where you can authorize the application.");

        // Wait for the user to authorize the application and extract the redirect URI
        System.out.println("Enter the redirect URI after authorization:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String redirectUri = br.readLine();

        // Extract access token from redirect URI
        String accessToken = extractAccessToken(redirectUri);

        // Create token response
        GoogleTokenResponse response = new GoogleTokenResponse();
        response.setAccessToken(accessToken);

        // Store the credentials
        return flow.createAndStoreCredential(response, null);
    }

    public static List<Event> getEvents() throws IOException, Exception {
        Credential credential = authorize();
        Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        System.out.println("wssalna hna service" + service.toString());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems();
    }

    public static void main(String[] args) {
        try {
            List<Event> events = getEvents();
            if (events.isEmpty()) {
                System.out.println("No upcoming events found.");
            } else {
                System.out.println("Upcoming events");
                for (Event event : events) {
                    System.out.println(event.getSummary());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

    private static String extractAccessToken(String redirectUri) {
        String accessToken = null;
        try {
            String[] parts = redirectUri.split("#|\\?|&");
            for (String part : parts) {
                if (part.startsWith("access_token=")) {
                    accessToken = part.substring("access_token=".length());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
