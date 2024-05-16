package mygroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.client.FindIterable;

import javafx.application.Application;
import javafx.stage.Stage;
import mygroup.persistence.DAO.DAOTache;
import mygroup.presentation.GetTasks.GetTasksController;
import mygroup.presentation.GetTasks.GetTasksView;
import mygroup.presentation.NewDocument.AddDocumentController;
import mygroup.presentation.NewDocument.AddDocumentView;
import mygroup.presentation.NewList.AddListController;
import mygroup.presentation.NewList.AddListView;
import mygroup.presentation.archive.ArchiveFormController;
import mygroup.presentation.archive.ArchiveFormView;
import mygroup.presentation.listes.ListeFormView;
import mygroup.presentation.login.LoginController;
import mygroup.presentation.login.LoginFormView;
import mygroup.presentation.projet_detail.ProjetDetailController;
import mygroup.presentation.projet_detail.Projet_Detail_View;
import mygroup.presentation.projets.ProjetsFormController;
import mygroup.presentation.projets.ProjetsFormView;
import mygroup.presentation.seance.SeanceFormController;
import mygroup.presentation.seance.SeanceFormView;
import mygroup.presentation.seance_ajoute.SceanceAjouteController;
import mygroup.presentation.seance_ajoute.SceanceAjouteView;
import mygroup.presentation.seance.SeanceFormView;
import mygroup.presentation.tache_ajoute.ControllerFromTacheAjout;
import mygroup.presentation.tache_ajoute.addTacheview;
import mygroup.presentation.taches.TachesFormController;
import mygroup.presentation.taches.TachesFormView;

import java.time.LocalDateTime;


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

        // ProjetDetailController con = new ProjetDetailController() ;
        // Projet_Detail_View view = new Projet_Detail_View(con) ;
        // view.start(primaryStage) ;

        // SeanceFormView seance = new SeanceFormView();
        // seance.start(primaryStage);

        // SeanceFormView view = new SeanceFormView();
        // view.start(primaryStage);


        // Projet_Detail_View view = new Projet_Detail_View();
        // view.start(primaryStage);

        // SceanceAjouteView view = new SceanceAjouteView();
        // view.start(primaryStage);
        
        // TachesFormView view = new TachesFormView() ;
        // view.start(primaryStage) ;

        // SceanceFormController con = new SceanceFormController() ;
        // SeanceFormView view = new SeanceFormView(con) ;
        // view.start(primaryStage) ;

        // addTacheview vue = new addTacheview() ;
        // ControllerFromTacheAjout con = new ControllerFromTacheAjout(vue) ;
        // AddDocumentView view = new AddDocumentView(con) ;
        // view.start(primaryStage) ;

        // LoginFormView view = new LoginFormView(primaryStage) ;
        // view.start(primaryStage) ;

        ListeFormView view = new ListeFormView() ;
        view.start(primaryStage) ;


        // presentation.tache_detail.screen view = new presentation.tache_detail.screen() ;
        // presentation.tache_detail.controleur con = new presentation.tache_detail.controleur(view, null) ;
        // view.start(primaryStage) ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}