
import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.login.LoginController;
import presentation.login.LoginFormView;
import presentation.seance.SceanceFormController;
import presentation.seance.SeanceFormView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListeFormController controller = new ListeFormController();
        ListeFormView Seance = new ListeFormView(controller);
        Seance.start(primaryStage);
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
