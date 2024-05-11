import java.time.LocalDateTime;
import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.client.FindIterable;

import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DAO.DAOTache;
import presentation.GetTasks.GetTasksController;
import presentation.GetTasks.GetTasksView;
import presentation.NewList.AddListController;
import presentation.NewList.AddListView;
import presentation.archive.ArchiveFormController;
import presentation.archive.ArchiveFormView;
import presentation.listes.ListeFormView;
import presentation.login.LoginController;
import presentation.login.LoginFormView;
import presentation.projet_detail.ProjetDetailController;
import presentation.projet_detail.Projet_Detail_View;
import presentation.projets.ProjetsFormController;
import presentation.projets.ProjetsFormView;
import presentation.seance.SeanceFormController;
import presentation.seance.SeanceFormView;
import presentation.seance_ajoute.SceanceAjouteController;
import presentation.seance_ajoute.SceanceAjouteView;
import presentation.seance.SeanceFormView;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.tache_ajoute.ViewFromTacheAjout;
import presentation.taches.TachesFormController;
import presentation.taches.TachesFormView;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ListeFormController controller = new ListeFormController();
        // ListeFormView Seance = new ListeFormView();
        // Seance.start(primaryStage);

        // ProjetsFormView projets = new ProjetsFormView();
        // projets.start(primaryStage);

        // ListeFormController controller = new ListeFormController();

        // ListeFormView projets = new ListeFormView();
        // projets.start(primaryStage);

        // ArchiveFormView archive = new ArchiveFormView();
        // archive.start(primaryStage);

        // LoginFormView login = new LoginFormView(primaryStage);
        // login.start(primaryStage);

        // ControllerFromTacheAjout controller = new ControllerFromTacheAjout();
        // ViewFromTacheAjout view = new ViewFromTacheAjout(controller);
        // view.start(primaryStage);

        // ProjetDetailController con = new ProjetDetailController() ;
        // Projet_Detail_View view = new Projet_Detail_View(con) ;
        // view.start(primaryStage) ;


        // SeanceFormView seance = new SeanceFormView();
        // seance.start(primaryStage);

        // SeanceFormView view = new SeanceFormView();
        // view.start(primaryStage);

        // ProjetDetailController con = new ProjetDetailController();
        // Projet_Detail_View view = new Projet_Detail_View(con);
        // view.start(primaryStage);

        // SceanceAjouteView view = new SceanceAjouteView();
        // view.start(primaryStage);
        
        TachesFormView view = new TachesFormView() ;
        view.start(primaryStage) ;

        // SceanceFormController con = new SceanceFormController() ;
        // SeanceFormView view = new SeanceFormView(con) ;
        // view.start(primaryStage) ;
  


    }

    public static void main(String[] args) {
        launch(args);
    }
}
