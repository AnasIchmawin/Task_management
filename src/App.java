
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
import presentation.login.LoginController;
import presentation.login.LoginFormView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController controller = new LoginController();
        LoginFormView login = new LoginFormView(controller);
        login.start(primaryStage);
    }

    public static void main(String[] args) throws IOException {
        try {
            DBConnection.getInstance();
            System.out.println("Connexion à la base de données réussie");

        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données");
        }
        // Lancer l'application
        launch(args);
    }
}
