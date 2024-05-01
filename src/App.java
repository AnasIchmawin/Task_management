
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
<<<<<<< HEAD
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.login.LoginController;
import presentation.login.LoginFormView;
import presentation.projets.ProjetsFormController;
import presentation.projets.ProjetsFormView;
import presentation.seance.SceanceFormController;
import presentation.seance.SeanceFormView;
=======
import presentation.login.LoginController;
import presentation.login.LoginFormView;
>>>>>>> d7133f8c1baed819eb795833feac26ab3f6693b7

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
<<<<<<< HEAD
        // ListeFormController controller = new ListeFormController();
        // ListeFormView Seance = new ListeFormView(controller);
        // Seance.start(primaryStage);

        // ProjetsFormController controller = new ProjetsFormController();
        // ProjetsFormView projets = new ProjetsFormView(controller);
        // projets.start(primaryStage);

        ArchiveFormView archive = new ArchiveFormView();
        archive.start(primaryStage);
=======
        LoginController controller = new LoginController();
        LoginFormView login = new LoginFormView(controller);
        login.start(primaryStage);
>>>>>>> d7133f8c1baed819eb795833feac26ab3f6693b7
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
