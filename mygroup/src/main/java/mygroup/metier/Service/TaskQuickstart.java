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
import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.TasksScopes;
import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.TaskList;
import com.google.api.services.tasks.model.TaskLists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mygroup.presentation.GetTaskFromCalendar.ItemTask;
import com.google.api.client.util.DateTime;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class TaskQuickstart {
    private static final String APPLICATION_NAME = "Task-Management";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String DATA_STORE_DIR = "tokens";
    private static final List<String> SCOPES_TASKS = Arrays.asList(TasksScopes.TASKS_READONLY);
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

    public static ObservableList<ItemTask> getTasks(NetHttpTransport HTTP_TRANSPORT, String date)
            throws IOException, GeneralSecurityException {

        if (!hasPermissions(HTTP_TRANSPORT, SCOPES_TASKS)) {
            // Rediriger l'utilisateur vers le navigateur pour obtenir les autorisations
            return null;
        }

        Credential credential = getCredentials(HTTP_TRANSPORT, SCOPES_TASKS);
        Tasks tasksService = new Tasks.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        TaskLists taskLists = tasksService.tasklists().list().execute();
        ObservableList<ItemTask> items = FXCollections.observableArrayList();
        for (TaskList taskList : taskLists.getItems()) {
            com.google.api.services.tasks.model.Tasks tasks = tasksService.tasks().list(taskList.getId()).execute();
            for (Task task : tasks.getItems()) {
                if (task.getDue() != null) {
                    String dueDate = getFormattedDateTime(task.getDue());
                    if (dueDate.contains(date)) {
                        String title = task.getTitle();
                        String description = task.getNotes();
                        items.add(new ItemTask(false, title, description, dueDate));
                    }
                }
            }
        }
        return items;
    }

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