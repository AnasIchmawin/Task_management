package mygroup;

import javafx.application.Application;
import javafx.stage.Stage;
import mygroup.metier.Service.CalendarQuickstart;
import mygroup.metier.Service.TaskQuickstart;
import mygroup.presentation.projets.ProjetsFormView;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ProjetsFormView projets = new ProjetsFormView();
        // projets.start(primaryStage);

        // ListeFormController controller = new ListeFormController();

        // ArchiveFormView archive = new ArchiveFormView();
        // archive.start(primaryStage);

        // LoginFormView login = new LoginFormView(primaryStage);
        // login.start(primaryStage);

        // addTacheview view = new addTacheview();
        // view.start(primaryStage);


        // SeanceFormView seance = new SeanceFormView();
        // seance.start(primaryStage);

        // SeanceFormView view = new SeanceFormView();
        // view.start(primaryStage);

        // ProjectStatisticsView view = new ProjectStatisticsView();
        // view.start(primaryStage);


        // SceanceAjouteView view = new SceanceAjouteView();
        // view.start(primaryStage);

        // TachesFormView view = new TachesFormView() ;
        // view.start(primaryStage) ;

        // SceanceFormController con = new SceanceFormController() ;
        // SeanceFormView view = new SeanceFormView() ;
        // view.start(primaryStage) ;

        // addTacheview vue = new addTacheview() ;
        // ControllerFromTacheAjout con = new ControllerFromTacheAjout(vue) ;
        // AddDocumentView view = new AddDocumentView(con) ;
        // view.start(primaryStage) ;

        LoginFormView view = new LoginFormView(primaryStage) ;
        view.start(primaryStage) ;

        // ListeFormView view = new ListeFormView();
        // view.start(primaryStage);
        // ListeFormView view = new ListeFormView();
        // view.start(primaryStage);
        // presentation.tache_detail.screen view = new
        // presentation.tache_detail.screen() ;
        // presentation.tache_detail.controleur con = new
        // presentation.tache_detail.controleur(view, null) ;
        // view.start(primaryStage) ;

        // tacheDetailController con = new tacheDetailController("663bbacc6d8e33b7e243ded3", primaryStage) ;

        // ProjetDetailView view = new ProjetDetailView();
        // view.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        // Appel de la méthode pour effacer le contenu du token
        TaskQuickstart.clearTokenContent();
        CalendarQuickstart.clearTokenContent();
        System.out.println("les tokens sont effacés");
    }

    public static void main(String[] args) {
        launch(args);
    }
}