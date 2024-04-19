

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;

import presentation.login.*;;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialiser le contrôleur et la vue
        LoginController controller = new LoginController();
        LoginFormView view = new LoginFormView(controller);
        view.start(primaryStage);
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
