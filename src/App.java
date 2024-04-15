import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
import persistence.DAO.document;
import persistence.DAO.tache;
import presentation.archive.screenArchive;
import presentation.listes.screenList;
import presentation.login.screenLogin;
import presentation.projets.screenProjets;
import presentation.seance.screenSeance;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        screenList list = new screenList();        
        list.start(primaryStage); // Appelez la méthode start()

    }

    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            DBConnection dbConnection = DBConnection.getInstance();
            System.out.println("Connexion à la base de données réussie");

        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données");
        }
        // Lancer l'application
        launch(args);
    }
}
