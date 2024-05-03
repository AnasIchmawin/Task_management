import javafx.application.Application;
import javafx.stage.Stage;
import presentation.projet_detail.ProjetDetailController;
import presentation.projet_detail.Projet_Detail_View;





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

        ProjetDetailController controller = new ProjetDetailController();
        Projet_Detail_View taches = new Projet_Detail_View(controller);
        taches.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
