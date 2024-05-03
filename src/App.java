import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DBConnection;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.login.LoginController;
import presentation.login.LoginFormView;
import presentation.projets.ProjetsFormController;
import presentation.projets.ProjetsFormView;
import presentation.seance.SceanceFormController;
import presentation.seance.SeanceFormView;
import presentation.tache_detail.controleur;
import presentation.tache_detail.screen;
import presentation.taches.TachesFormController;
import presentation.taches.TachesFormView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ListeFormController controller = new ListeFormController();
        // ListeFormView Seance = new ListeFormView(controller);
        // Seance.start(primaryStage);

        // ProjetsFormController controller = new ProjetsFormController();
        // ProjetsFormView projets = new ProjetsFormView(controller);
        // projets.start(primaryStage);

        // ListeFormController controller = new ListeFormController();
        // ListeFormView projets = new ListeFormView(controller);
        // projets.start(primaryStage);

        // ArchiveFormController controller = new ArchiveFormController();
        // ArchiveFormView archive = new ArchiveFormView(controller);
        // archive.start(primaryStage);

        controleur controller = new controleur();
        screen taches = new screen(controller);
        taches.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
