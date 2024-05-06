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
import presentation.listes.ListeFormController;
import presentation.listes.ListeFormView;
import presentation.login.LoginController;
import presentation.login.LoginFormView;
import presentation.projet_detail.ProjetDetailController;
import presentation.projet_detail.Projet_Detail_View;
import presentation.projets.ProjetsFormController;
import presentation.projets.ProjetsFormView;
import presentation.seance_ajoute.SeanceAjouteFormController;
import presentation.tache_ajoute.ControllerFromTacheAjout;
import presentation.tache_ajoute.ViewFromTacheAjout;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ListeFormController controller = new ListeFormController();
        // ListeFormView Seance = new ListeFormView(controller);
        // Seance.start(primaryStage);

        // ProjetsFormView projets = new ProjetsFormView();
        // projets.start(primaryStage);

        // ListeFormController controller = new ListeFormController();
        // ListeFormView projets = new ListeFormView(controller);
        // projets.start(primaryStage);

        // ArchiveFormController controller = new ArchiveFormController();
        // ArchiveFormView archive = new ArchiveFormView(controller);
        // archive.start(primaryStage);
        

        // ListeFormView view = new ListeFormView() ; 
        // view.start(primaryStage);

        LoginFormView loginFormView = new LoginFormView(primaryStage);
        loginFormView.start(primaryStage);

        // ControllerFromTacheAjout controller = new ControllerFromTacheAjout();
        // ViewFromTacheAjout view = new ViewFromTacheAjout(controller);
        // view.start(primaryStage);
  

    }
    public static void main(String[] args) {
        launch(args);
    }
}
