import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
import presentation.listes.ListFormController;
import presentation.listes.ListeFormView;
import presentation.login.*;
import presentation.projet_detail.screen;
import presentation.seance.screenSeance;;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListFormController controller = new ListFormController();
        ListeFormView listView = new ListeFormView(controller);
        listView.start(primaryStage);
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
